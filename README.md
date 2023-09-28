<p align="center">
  <img src="https://gelecegiyazanlar.turkcell.com.tr/sites/default/files/etkinlik/etkinlik_resmi/2020-12/image-eteration-logo-dark_0.png" alt="Description of the image">
</p>

    
# Sipmle Banking

Bu proje, Eteration firması tarafından işe alım süreci için tarafıma gönderilen bir ödevdir. Projeyi kısaca özetlemem gerekirse, kullanıcı banka hesabı oluşturabilir. Bu hesap üzerinden para ekleyebilir ve çekebilir. Aynı zamanda kullanıcı, hesabındaki parayı kullanarak telefon faturasını ödeyebilir.

<p align="center">
  <img src="https://img.freepik.com/premium-vector/bank-building-money-bank-financing-money-exchange-financial-services-atm-giving-out-money_625536-194.jpg" alt="Description of the image">
</p>




## API Kullanımı

#### Hesap Oluşturur.

```http
  POST /api/v1/account/createAccount
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `createAccountRequest` | `CreateAccountRequest` | **Gerekli**. Hesap oluşturma isteği nesnesi. |

#### Tüm Hesapları Getirir.

```http
  GET /api/v1/account/findAll
```

| İşlem | HTTP Metodu     | Açıklama                       |
| :-------- | :------- | :-------------------------------- |
| `findAll`      | `GET` | Tüm öğeleri getirir. |

#### Hesap Numarasına Sahip Nesneyi Getirir.

```http
  GET /api/v1/account/getByAccountNumber/{accountNumber}
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `accountNumber` | `String` | Hesap Koduna Sahip Nesneyi Getirir. |

#### ID Kullanarak ID Sahip Nesneyi Getirir.

```http
  GET /api/v1/account/getById/{id}
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` |  Id ile hesabı getirir. |

#### Hesap Numarası Kullanarak Numaraya Sahip Nesneyi Siler.

```http
  DELETE /api/v1/account/deleteByAccountNumber/{accountNumber}
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `accountNumber` | `String` |  Hesap Numarası İle Silme. |

#### ID Kullanarak ID Sahip Nesneyi Siler.

```http
  DELETE /api/v1/account/deleteById/{id}
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` |  ID İle Silme. |

#### Hesaba Para Yatırma

```http
  POST /api/v1/account/credit
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `creditRequest` | `CreditRequest` |  Para yatırma. |

#### Hesabtan Para Çekme

```http
  POST /api/v1/account/debit
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `debitRequest` | `DebitRequest` |  Para Çekme. |

#### Telefon Faturası Odeme

```http
  POST /api/v1/account/payPhoneBill
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `paymentPhoneBillRequest` | `PaymentPhoneBillRequest` |  Fatura Odeme. |

## Postman Collection
Burada projemizin Postman koleksiyonunu bulabilirsiniz: [Postman Koleksiyonu](https://github.com/TheAykac/Simple-Banking/blob/master/collection/SimpleBanking.postman_collection.json)
  
## Geri Bildirim

Herhangi bir geri bildiriminiz varsa, lütfen farukomeraykac@gmail.com adresinden bana ulaşabilirsiniz.

  
## Kullanılan Teknolojiler

 Java - Hibernate - ORM - Maven - Spring Boot - AOP - JPA - RestAPI -Dev - PostgreSQL



  
