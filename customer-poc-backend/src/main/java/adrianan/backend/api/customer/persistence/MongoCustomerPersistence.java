package adrianan.backend.api.customer.persistence;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import adrianan.backend.api.customer.Customer;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

@Named("mongoCustomerPersistence")
public class MongoCustomerPersistence implements CustomerPersistence{
	
	private static final Logger logger = Logger.getLogger(MongoCustomerPersistence.class.getName());

	private MongodStarter runtime = MongodStarter.getDefaultInstance();
	private MongodProcess mongod = null;

	private MongodExecutable mongodExecutable = null;

	private MongoClient mongoClient = null;

	private CodecRegistry pojoCodecRegistry;

	@PostConstruct
	public void init() {
		try {
			int port = 12345;
			IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
					.net(new Net("localhost", port, Network.localhostIsIPv6())).build();
			this.mongodExecutable = runtime.prepare(mongodConfig);
			this.mongod = mongodExecutable.start();

			this.pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
					fromProviders(PojoCodecProvider.builder().automatic(true).build()));

			ServerAddress serverAddress = new ServerAddress("localhost", port);

			this.mongoClient = new MongoClient(serverAddress,
					MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());

			MongoCollection<Customer> collection = this.getCustomerCollection();

			List<Customer> customers = Arrays.asList(
					new Customer(this.generateNewId(), "Charles Babbage", "5 Devonshire Street London W11", "+55 11 9 4388 4994"),
					new Customer(this.generateNewId(), "Alan Turing", "Bletchley Hall Bletchley Park MK12", "+55 11 9 4388 4994"),
					new Customer(this.generateNewId(), "Timothy Berners-Lee", "Colehill Wimborne", "+55 11 9 4388 4994"));

			collection.insertMany(customers);
			
			logger.log(Level.INFO, "Embedded MongoDB and mongo client up and running");

		} catch (Exception e) {
			logger.log(Level.SEVERE, "exception initializing system", e);
		}
	}

	@PreDestroy
	public void destroy() {
		try {
			if (this.mongoClient != null) {
				this.mongoClient.close();
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "exception closing mongo client", e);
		}
		try {
			if (this.mongod != null) {
				this.mongod.stop();
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "exception stopping mongo daemon", e);
		}
		try {
			if (this.mongodExecutable != null) {
				this.mongodExecutable.stop();
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "exception stopping mongo application", e);
		}
	}

	@Override
	public List<Customer> findAll() {
		MongoCollection<Customer> collection = this.getCustomerCollection();
		List<Customer> result = collection.find().into(new ArrayList<Customer>());
		return result;
	}

	@Override
	public Customer findById(String id) {
		MongoCollection<Customer> customerCollection = this.getCustomerCollection();
		Bson filter = Filters.eq("cid", id);
		Customer result = customerCollection.find(filter).first();
		return result;
	}

	@Override
	public Customer update(Customer customer) {
		MongoCollection<Customer> customerCollection = this.getCustomerCollection();
		Bson filter = Filters.eq("cid", customer.getCid());
		customerCollection.updateOne(filter, Updates.combine(Updates.set("address", customer.getAddress()), Updates.set("phone", customer.getPhone()),
				Updates.set("name", customer.getName())));
		return customer;
	}

	@Override
	public Customer create(Customer customer) {
		MongoCollection<Customer> collection = this.getCustomerCollection();
		customer.setCid(this.generateNewId());
		collection.insertOne(customer);
		Customer result = customer;
		return result;
	}

	private String generateNewId() {
		return UUID.randomUUID().toString();
	}

	@Override
	public void remove(String id) {
		MongoCollection<Customer> customerCollection = this.getCustomerCollection();
		Bson filter = Filters.eq("cid", id);
		customerCollection.deleteOne(filter);
	}

	private MongoCollection<Customer> getCustomerCollection() {
		MongoDatabase database = this.mongoClient.getDatabase("customers-poc").withCodecRegistry(this.pojoCodecRegistry);
		MongoCollection<Customer> collection = database.getCollection("customers", Customer.class);
		return collection;
	}

}
