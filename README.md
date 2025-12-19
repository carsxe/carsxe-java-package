# 🚗 CarsXE Java API Library

The **CarsXE Java API Library** is a powerful and developer-friendly library that enables seamless integration with the CarsXE API. Access a wide range of vehicle data, including VIN decoding, market value estimation, license plate recognition, vehicle history, lien & theft checks, and more.

**Java Version:** Java 21 LTS

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
  <artifactId>carsxe</artifactId>
  <version>1.0.4</version>
</dependency>
```

---

## 🛠️ Configuration

To use the library, you need to create an instance of the `CarsXE` class with your API key:

```java
import io.github.carsxe.CarsXE;

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

Map<String, Object> specs = carsxe.specs(params);
System.out.println(specs);
```

### Market Value

```java
Map<String, String> params = new HashMap<>();
params.put("vin", "WBAFR7C57CC811956");

Map<String, Object> marketValue = carsxe.marketvalue(params);
System.out.println(marketValue);
```

### Vehicle History

```java
Map<String, String> params = new HashMap<>();
params.put("vin", "WBAFR7C57CC811956");

Map<String, Object> history = carsxe.history(params);
System.out.println(history);
```

### License Plate Decoder

```java
Map<String, String> params = new HashMap<>();
params.put("plate", "7XER187");
params.put("state", "CA");
params.put("country", "US");

Map<String, Object> plateInfo = carsxe.platedecoder(params);
System.out.println(plateInfo);
```

### License Plate Image Recognition

```java
String imageUrl = "https://api.carsxe.com/img/apis/plate_recognition.JPG";

Map<String, Object> plateRecognition = carsxe.plateImageRecognition(imageUrl);
System.out.println(plateRecognition);
```

### VIN OCR from Image

```java
String imageUrl = "https://user-images.githubusercontent.com/5663423/30922082-64edb4fa-a3a8-11e7-873e-3fbcdce8ea3a.png";

Map<String, Object> vinOcr = carsxe.vinOcr(imageUrl);
System.out.println(vinOcr);
```

### Year, Make, and Model Search

```java
Map<String, String> params = new HashMap<>();
params.put("year", "2023");
params.put("make", "Toyota");
params.put("model", "Camry");

Map<String, Object> ymm = carsxe.yearMakeModel(params);
System.out.println(ymm);
```

### Vehicle Images

```java
Map<String, String> params = new HashMap<>();
params.put("make", "BMW");
params.put("model", "X5");
params.put("year", "2019");

Map<String, Object> images = carsxe.images(params);
System.out.println(images);
```

### Vehicle Recalls

```java
Map<String, String> params = new HashMap<>();
params.put("vin", "1C4JJXR64PW696340");

Map<String, Object> recalls = carsxe.recalls(params);
System.out.println(recalls);
```

### International VIN Decoder

```java
Map<String, String> params = new HashMap<>();
params.put("vin", "WF0MXXGBWM8R43240");

Map<String, Object> internationalVin = carsxe.internationalVinDecoder(params);
System.out.println(internationalVin);
```

### OBD Codes Decoder

```java
Map<String, String> params = new HashMap<>();
params.put("code", "P0115");

Map<String, Object> obdCodes = carsxe.obdcodesdecoder(params);
System.out.println(obdCodes);
```

### Lien & Theft Check

```java
Map<String, String> params = new HashMap<>();
params.put("vin", "2C3CDXFG1FH762860");

Map<String, Object> lienTheft = carsxe.LienAndTheft(params);
System.out.println(lienTheft);
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
- `LienAndTheft` – Check for lien and theft records on a vehicle

Refer to the [CarsXE API Documentation](https://api.carsxe.com/docs) for more details about parameters and response formats.
