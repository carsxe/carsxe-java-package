# 🚗 CarsXE Java API Library

The **CarsXE Java API Library** is a powerful and developer-friendly library that enables seamless integration with the CarsXE API. Access a wide range of vehicle data, including VIN decoding, market value estimation, license plate recognition, vehicle history, and more.

🌐 **Website:** [https://api.carsxe.com](https://api.carsxe.com)  
📄 **Docs:** [https://api.carsxe.com/docs](https://api.carsxe.com/docs)  
📦 **All Products:** [https://api.carsxe.com/all-products](https://api.carsxe.com/all-products)

---

## 🚀 Installation

### Using Maven

Add the following dependency to your `pom.xml` file:

```xml
<dependency>
  <groupId>io.github.carsxe</groupId>
  <artifactId>carsxe-api</artifactId>
  <version>1.0.1</version>
</dependency>
```

---

## 🛠️ Configuration

To use the library, you need to create an instance of the `CarsXE` class with your API key:

```java
import io.github.carsxe;

CarsXE carsxe = new CarsXE("YOUR_API_KEY");
```

Replace `YOUR_API_KEY` with your own CarsXE API key. You can get your API key by signing up at [CarsXE](https://api.carsxe.com).

---

## 📖 Usage

The CarsXE Java library provides methods corresponding to multiple endpoints. Below are examples for **all available products**:

### VIN Specifications

```java
Map<String, String> params = new HashMap<>();
params.put("vin", "WBAFR7C57CC811956");

String specs = carsxe.specs(params);
System.out.println(specs);
```

### Market Value

```java
Map<String, String> params = new HashMap<>();
params.put("vin", "WBAFR7C57CC811956");

String marketValue = carsxe.marketvalue(params);
System.out.println(marketValue);
```

### Vehicle History

```java
Map<String, String> params = new HashMap<>();
params.put("vin", "WBAFR7C57CC811956");

String history = carsxe.history(params);
System.out.println(history);
```

### License Plate Decoder

```java
Map<String, String> params = new HashMap<>();
params.put("plate", "7XER187");
params.put("state", "CA");
params.put("country", "US");

String plateInfo = carsxe.platedecoder(params);
System.out.println(plateInfo);
```

### License Plate Image Recognition

```java
String imageUrl = "https://api.carsxe.com/img/apis/plate_recognition.JPG";

String plateRecognition = carsxe.plateImageRecognition(imageUrl);
System.out.println(plateRecognition);
```

### VIN OCR from Image

```java
String imageUrl = "https://user-images.githubusercontent.com/5663423/30922082-64edb4fa-a3a8-11e7-873e-3fbcdce8ea3a.png";

String vinOcr = carsxe.vinOcr(imageUrl);
System.out.println(vinOcr);
```

### Year, Make, and Model Search

```java
Map<String, String> params = new HashMap<>();
params.put("year", "2023");
params.put("make", "Toyota");
params.put("model", "Camry");

String ymm = carsxe.yearMakeModel(params);
System.out.println(ymm);
```

### Vehicle Images

```java
Map<String, String> params = new HashMap<>();
params.put("make", "BMW");
params.put("model", "X5");
params.put("year", "2019");

String images = carsxe.images(params);
System.out.println(images);
```

### Vehicle Recalls

```java
Map<String, String> params = new HashMap<>();
params.put("vin", "1C4JJXR64PW696340");

String recalls = carsxe.recalls(params);
System.out.println(recalls);
```

### International VIN Decoder

```java
Map<String, String> params = new HashMap<>();
params.put("vin", "WF0MXXGBWM8R43240");

String internationalVin = carsxe.internationalVinDecoder(params);
System.out.println(internationalVin);
```

### OBD Codes Decoder

```java
Map<String, String> params = new HashMap<>();
params.put("code", "P0115");

String obdCodes = carsxe.obdcodesdecoder(params);
System.out.println(obdCodes);
```

---

## 📋 Endpoints

Here is the list of supported endpoints:

- `specs` – Decode VIN & get full vehicle specifications
- `internationalVinDecoder` – Decode VIN with worldwide support
- `platedecoder` – Decode license plate info (plate, state, country)
- `marketvalue` – Estimate vehicle market value based on VIN
- `history` – Retrieve vehicle history (ownership, accidents, etc.)
- `images` – Fetch images by make, model, year, trim
- `recalls` – Get safety recall data for a VIN
- `plateImageRecognition` – Read & decode plates from images
- `vinOcr` – Extract VINs from images using OCR
- `yearMakeModel` – Query vehicle by year, make, model, and trim (optional)
- `obdcodesdecoder` – Decode OBD error/diagnostic codes

Refer to the [CarsXE API Documentation](https://api.carsxe.com/docs) for more details about parameters and response formats.

---

## 🤝 Contributing

We welcome contributions to the CarsXE Java API Library! If you have ideas for improvements, bug fixes, or new features, please feel free to submit a pull request or open an issue on our GitHub repository.

PRs and issues are welcome at:  
🔗 [https://github.com/carsxe/carsxe-java-package](https://github.com/carsxe/carsxe-java-package)

---

## 📄 License

This library is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
