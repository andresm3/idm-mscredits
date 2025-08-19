# idm-mscredits
ms to manage credits

# Environment Variables
add the environment variables to MscreditsApplication

```
MONGODB_URL=mongodb+srv://user:pwd@cluster/?retryWrites=true&w=majority;MONGODB_DATABASE=db
```

# Endpoints

```
curl --location 'localhost:8080/credits/createCreditCard' \
--header 'Content-Type: application/json' \
--data '{
    "creditCard": "4242212123232020",
    "productId": "64acbc39692e840c87d2d6c1",
    "client": {
        "id": "64b8c582f621885376bddce5",
        "documentType": "DNI",
        "documentNumber": "45454656",
        "firstName": "Miguel",
        "lastName": "Grau",
        "type": 1,
        "profile": 2,
        "active": 1
    },
    "type": 3,
    "creditTotal": 10000,
    "creditBalance": 10000,
    "monthlyPaymentExpirationDay": 15,
    "percentageInterestRate": 29,
    "active": true,
    "number": 1017
}'
```