package org.example;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.daos.jaxb.FarmsSupplyProductsBoughtJAXB;
import org.example.daos.mybatis.*;
import org.example.domain.*;
import org.example.interfaces.IDAO;
import org.example.utils.enums.UnitMeasurement;
import org.example.utils.myBatis.mappers.AnimalMapper;
import java.sql.Date;
import org.example.daos.json.*;
import org.example.domain.*;
import org.example.presentacion.Service;
import org.example.utils.enums.UnitMeasurement;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;
import java.util.Properties;

public class Main {

    private static final Logger logger = LogManager.getLogger();


    public static void main(String[] args) throws Exception {
        IDAO< Animal> animalIDAO = new AnimalMyBatis();
//        System.out.println(animalIDAO.findById(1));

        Animal animal = new Animal();
        animal.setName("Goat");
//        System.out.println(animalIDAO.insert(animal));
        animal.setName("Cabra");
//        System.out.println(animalIDAO.updateById(20,animal));
//        System.out.println(animalIDAO.deleteById(12));
//        animalIDAO.findAll().forEach(System.out::println);
//        System.out.println(animalIDAO.findById(9));

        IDAO<Owner> ownerIDAO = new OwnerMyBatis();

        Owner owner = new Owner();
        owner.setFullName("Marco");
        owner.setPhone("654654654");
        owner.setEmail("marco@gmail.com");
//        System.out.println(ownerIDAO.insert(owner));
//        System.out.println();
//        System.out.println();
//        System.out.println(ownerIDAO.updateById(13,owner));
//        System.out.println(ownerIDAO.findById(1));
//        System.out.println(ownerIDAO.deleteById(13));
//        ownerIDAO.findAll().forEach(System.out::println);

        IDAO<Farm> farmIDAO = new FarmMyBatis();
        Farm farm = new Farm();
        farm.setName("Granjita");
        farm.setAddress("Rancho");
        farm.setOwner_id(1);
//        System.out.println(farmIDAO.insert(farm));
        farm.setAddress("Villa");
//        System.out.println(farmIDAO.updateById(12,farm));
//        System.out.println(farmIDAO.deleteById(12));
//        System.out.println(farmIDAO.findById(8));
//        farmIDAO.findAll().forEach(System.out::println);

        IDAO<Product> productIDAO = new ProductMyBatis();
        Product product = new Product();
        product.setName("Honey");
        product.setUnitMeasurement(UnitMeasurement.L.unit);
        product.setPrice(5);
//        System.out.println(productIDAO.insert(product));
//        System.out.println(productIDAO.updateById(6,product));
//        System.out.println(productIDAO.findById(6));
//        productIDAO.findAll().forEach(System.out::println);

        IDAO<Store> storeIDAO = new StoreMyBatis();
        Store store = new Store();
        store.setName("Tiendita");
        store.setAddress("Street 54");
//        System.out.println(storeIDAO.insert(store));
        store.setName("Tienditita");
//        System.out.println(storeIDAO.updateById(8,store));
//        System.out.println(storeIDAO.deleteById(8));
//        System.out.println(storeIDAO.findById(5));
//        storeIDAO.findAll().forEach(System.out::println);

        IDAO<FarmSupplyProductBought> farmSupplyDAO = new FarmSupplyProductBoughtMyBatis();
        FarmSupplyProductBought f = new FarmSupplyProductBought();
        f.setPurchaseDate(new Date(System.currentTimeMillis()));
        f.setTotal(50d);
        f.setAmount(10);
        f.setProduct_id(1);
        f.setFarm_id(1);
//        System.out.println(farmSupplyDAO.insert(f));

        f.setAmount(5);
        f.setTotal(25d);
//        farmSupplyDAO.updateById(7,f);
//        farmSupplyDAO.deleteById(7);
//        System.out.println(farmSupplyDAO.findById(4));
//        farmSupplyDAO.findAll().forEach(System.out::println);

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.INDENT_OUTPUT,true);
//    objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);//Takes the class root name, if it does not have takes the class name
    ExtendableBean bean = new ExtendableBean(1,"My bean",new Date(System.currentTimeMillis()));
    bean.add("attr1", "val1");
    bean.add("attr2", "val2");

    BeanWithInclude beanI = new BeanWithInclude(1, "My bean");
        String res1 = objectMapper.writeValueAsString(beanI);
        System.out.println(res1);
        System.out.println();
    String res = objectMapper.writeValueAsString(bean);
        System.out.println(res);

        System.out.println();
        String enumString = objectMapper.writeValueAsString(TypeEnumWithValue.TYPE1);
        System.out.println(enumString); //WRAP_ROOT_VALUE affected the result

        System.out.println();
        ExtendableBean select = objectMapper.readerFor(ExtendableBean.class).readValue(res);
        System.out.println(select);
//    Employee[] emp = objectMapper.readValue(new File("src/main/resources/json/test.json"),Employee[].class);
//    for(Employee e : emp){
//        System.out.println(e);
//    }
//        System.out.println(emp);
//        Map<?,?> empMap = objectMapper.readValue(new FileInputStream("src/main/resources/json/test.json"),Map.class);
//        for (Map.Entry<?, ?> entry : empMap.entrySet())
//        {
//            System.out.println("\n----------------------------\n"+entry.getKey() + "=" + entry.getValue()+"\n");
//        }

//        Employee employee = new Employee();
//        employee.setAge(20);
//        Address address = new Address();
//        address.setCity("City1");
//        address.setStreet("Street1");
//        address.setZipcode(85870);
//        employee.setAddress(address);
//        employee.setDesignation("Porgammer");
//        employee.setName("Juan");
//        employee.setSalary(new BigDecimal(2000));
//
//        Employee[] list = {employee};
//
//        String jsonInString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
//        objectMapper.configure(SerializationFeature.INDENT_OUTPUT,true);
//        objectMapper.writeValue(new File("src/main/resources/json/test.json"),list);


        Properties properties = new Properties();
        properties.load(Main.class.getClassLoader().getResourceAsStream("env.properties"));
        AnimalJSON animalJSON = new AnimalJSON(properties.getProperty("json.animal"));

        Animal animal = new Animal();
        animal.setName("Goat");
        Animal animal2 = new Animal();
        animal2.setName("Vaca");
//        animalJSON.insert(animal);
//        animalJSON.insert(animal2);

//        animalJSON.updateById(2,animal);
//        animalJSON.deleteById(1);
            //animalJSON.findAll().forEach(System.out::println);
//        System.out.println(animalJSON.findById(1));

        FarmJSON farmJSON = new FarmJSON(properties.getProperty("json.farm"));
        Farm farm = new Farm();
        farm.setName("JoyFarm");
        farm.setOwner_id(1);
        farm.setAddress("Town 1");
        Farm farm2 = new Farm();
        farm2.setName("FARM");
        farm2.setOwner_id(1);
        farm2.setAddress("Town 2");
//        farmJSON.insert(farm);
//        farmJSON.insert(farm2);
        farm.setName("extra");
        farm.setOwner_id(1);
        farm.setAddress("Address 1");
//        farmJSON.updateById(1,farm);
//        farmJSON.deleteById(1);
//        farmJSON.findAll().forEach(System.out::println);
//        System.out.println(farmJSON.findById(2));

        OwnerJSON ownerJSON = new OwnerJSON(properties.getProperty("json.owner"));
        Owner owner = new Owner();
        owner.setFullName("Juan Perez");
        owner.setPhone("654456654");
        owner.setEmail("juan@gmail.com");
        Owner owner1 = new Owner();
        owner1.setFullName("Jhon Wick");
        owner1.setPhone("989879877");
        owner1.setEmail("jhon@gmail.com");
//        ownerJSON.insert(owner);
//        ownerJSON.insert(owner1);

        owner.setEmail("perez@gmail.com");
//        ownerJSON.updateById(1,owner);

//        ownerJSON.deleteById(1);
//        ownerJSON.findAll().forEach(System.out::println);
//        System.out.println(ownerJSON.findById(2));

        ProductJSON productJSON = new ProductJSON(properties.getProperty("json.product"));

        Product product = new Product();
        product.setName("Meat");
        product.setPrice(5);
        product.setUnitMeasurement(UnitMeasurement.oz.unit);
        Product product1 = new Product();
        product1.setName("Eggs");
        product1.setPrice(10);
        product1.setUnitMeasurement(UnitMeasurement.pc.unit);

//        productJSON.insert(product);
//        productJSON.insert(product1);

        product.setName("Meat Premium");
//        productJSON.updateById(3,product);

//        productJSON.findAll().forEach(System.out::println);

//        System.out.println(productJSON.findById(1));

//        System.out.println(productJSON.deleteById(1));


        StoreJSON storeJSON = new StoreJSON(properties.getProperty("json.store"));
        Store store = new Store();
        store.setAddress("Street 4");
        store.setName("OXXO");

        Store store1 = new Store();
        store1.setAddress("Street 5");
        store1.setName("7ELEVEN");

//        storeJSON.insert(store);
//        storeJSON.insert(store1);

        store1.setName("extra");
//        storeJSON.updateById(3,store1);

//        storeJSON.deleteById(1);

//            storeJSON.findAll().forEach(System.out::println);
//        System.out.println(storeJSON.findById(3));

        FarmsSupplyProductBoughtJSON farmPurchasesJSON = new FarmsSupplyProductBoughtJSON(properties.getProperty("json.farmSupplyProductBought"));

        FarmSupplyProductBought f = new FarmSupplyProductBought();
        f.setFarm_id(1);
        f.setProduct_id(1);
        f.setPurchaseDate(new Date(System.currentTimeMillis()));
        f.setAmount(10);
        f.setTotal(100d);
        FarmSupplyProductBought f1 = new FarmSupplyProductBought();
        f1.setFarm_id(1);
        f1.setProduct_id(2);
        f1.setPurchaseDate(new Date(System.currentTimeMillis()));
        f1.setAmount(5);
        f1.setTotal(50d);

//        farmPurchasesJSON.insert(f);
//        farmPurchasesJSON.insert(f1);

        f.setProduct_id(3);
        f.setTotal(200d);
        f.setProduct_id(2);
//        farmPurchasesJSON.updateById(3,f);

//        farmPurchasesJSON.findAll().forEach(System.out::println);
//        farmPurchasesJSON.deleteById(1);
//        System.out.println(farmPurchasesJSON.findById(1));
    }

//    @JsonRootName(value = "typeValue")
    public enum TypeEnumWithValue {
        TYPE1(1,"Type A"), TYPE2(2, "Type 2");

        private Integer id;
        private String name;

        // standard constructors
        TypeEnumWithValue(int id,String name){
            this.id = id;
            this.name = name;
        }
        @JsonValue
        public String getName() {
            return name;
        }
    }

    public static class Employee {

        private int id;
        private String name;
        private int age;
        private BigDecimal salary;
        private String designation;
        private Address address;
        private long[] phoneNumbers;
        private Map<String, String> personalInformation;

        /*Getter and Setter Methods*/
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public BigDecimal getSalary() {
            return salary;
        }

        public void setSalary(BigDecimal salary) {
            this.salary = salary;
        }

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public long[] getPhoneNumbers() {
            return phoneNumbers;
        }

        public void setPhoneNumbers(long[] phoneNumbers) {
            this.phoneNumbers = phoneNumbers;
        }

        public Map<String, String> getPersonalInformation() {
            return personalInformation;
        }

        public void setPersonalInformation(Map<String, String> personalInformation) {
            this.personalInformation = personalInformation;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\n----- Employee Information-----\n");
            sb.append("ID: " + getId() + "\n");
            sb.append("Name: " + getName() + "\n");
            sb.append("Age: " + getAge() + "\n");
            sb.append("Salary: $" + getSalary() + "\n");
            sb.append("Designation: " + getDesignation() + "\n");
            sb.append("Phone Numbers: " + Arrays.toString(getPhoneNumbers()) + "\n");
            sb.append("Address: " + getAddress() + "\n");
            sb.append("Personal Information:" + getPersonalInformation() + "\n");
            sb.append("*****************************");
            return sb.toString();
        }
    }

    public static class Address {
        private String street;
        private String city;
        private int zipCode;

        public String getStreet() {
            return street;
        }
        public void setStreet(String street) {
            this.street = street;
        }
        public String getCity() {
            return city;
        }
        public void setCity(String city) {
            this.city = city;
        }
        public int getZipCode() {
            return zipCode;
        }
        public void setZipcode(int zipcode) {
            this.zipCode = zipcode;
        }

        @Override
        public String toString(){
            return getStreet() + ", "+getCity()+", "+getZipCode();
        }
    }

    @JsonIncludeProperties({ "id" })
    public static class BeanWithInclude {
        public int id;
        public String name;

        public BeanWithInclude(int id, String name){
            this.id = id;
            this.name = name;
        }
    }

//    @JsonPropertyOrder({"name","id"})
//    @JsonPropertyOrder(alphabetic = true)
//    @JsonRootName(value = "user")
//    @JsonIgnoreProperties({"id"}) //Ignore properties in class level // To ignore any unknown properties in JSON input without exception, we can set ignoreUnknown=true of @JsonIgnoreProperties annotation.
//    @JsonInclude(JsonInclude.Include.NON_NULL) // Exclude properties with empty/null/default values
//    @JsonIncludeProperties({ "name" }) //serializes and deserializes regard the properties, properties need to be public
//    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY) //serializes and deserializes regard the properties, you assign which type of properties
    public static class ExtendableBean{

//        @JsonIgnore
        private int id;
        @JsonProperty("full_name")
    private String name;
        @JsonSerialize(using = CustomDateSerializer.class)
        @JsonDeserialize(using = CustomDateDeserializer.class)
        private Date date;
//        @JsonRawValue
        private Map<String,String> properties;

        public ExtendableBean(){}

        public ExtendableBean(int id,String name,Date date){
            this.name = name;
            this.id = id;
            this.date = date;
            this.properties = new HashMap<>();
        }

//        @JsonGetter("full_name")
//        @JsonProperty("full_name")
        public String getName() {
            return name;
        }
//        @JsonSetter("full_name")
//    @JsonProperty("full_name")
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
            return id;
        }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

//            @JsonAnyGetter
//        public Map<String, String> getProperties() {
//            return properties;
//        }

        public void add(String key,String value){
            this.properties.put(key,value);
        }

    @Override
    public String toString() {
        return "ExtendableBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", properties=" + properties +
                '}';
    }
}

    public static class CustomDateSerializer extends StdSerializer<Date> {

        private static SimpleDateFormat formatter
                = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

        public CustomDateSerializer() {
            this(null);
        }

        public CustomDateSerializer(Class<Date> t) {
            super(t);
        }

        @Override
        public void serialize(
                Date value, JsonGenerator gen, SerializerProvider arg2)
                throws IOException, JsonProcessingException {
            gen.writeString(formatter.format(value));
        }
    }

    public static class CustomDateDeserializer
            extends StdDeserializer<Date> {

        private static SimpleDateFormat formatter
                = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

        public CustomDateDeserializer() {
            this(null);
        }

        public CustomDateDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Date deserialize(
                JsonParser jsonparser, DeserializationContext context)
                throws IOException {

            String date = jsonparser.getText();
            try {
                java.util.Date utilDate = formatter.parse(date);
                return new Date(utilDate.getTime());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

}