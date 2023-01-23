# Drones-API

A Rest API for drones which used to deliver medicine items

**Deployment Steps**

BUILD

- Open terminal
- Direct to the project folder
- Run maven build command (mvn clean install)

RUN

- Direct to the target folder
- Execute jar file (java -jar drones-0.0.1.jar)


VERIFICATION

- Application will be up in port 8085
- Download the postman collection (https://api.postman.com/collections/10148384-17de7341-397b-4c3a-ab5d-2ff40f233317?access_key=PMAT-01GQFTCZ99XRFEYYZXERGSV6BY)


ASSUMPTIONS MADE

- In memory H2 database is used along with pre loaded dataset (import.sql)
- Partial Medicine loading is not allowed, When a drone is load with medicines it will load all given medicines or none
- Drones will not be able to add along with medications
- (Unit test are partially completed)

API PAYLOADS

**1) Create a Drone **

    - USAGE: Registering a drone
    - URL: POST http://localhost:8085/v1/api/drones
    - Sample Request Body  :
        ` {
              "serialNumber": "cd-2002344",
              "model": "LIGHT_WEIGHT",
              "weightLimit": 100,
              "batteryCapacityPercentage":  50.6
           }`
    - Sample Success Response Body : 
          HTTP Code: 201 Created
        ` {
              "serialNumber": "cd-2002344",
              "model": "LIGHT_WEIGHT",
              "weightLimit": 100,
              "batteryCapacityPercentage": 50.6,
              "state": "IDLE"
          }`
        
**2) Get Drone by Serial Number **

    - USAGE: check drone details ex: battery level for a given drone
    
    - URL: GET  http://localhost:8085/v1/api/drones/cd-2002344
    
    - Sample Request Body  : -
           
    - Sample Success Response Body : 
          HTTP Code: 200 OK
        ` {
              "serialNumber": "cd-2002344",
              "model": "LIGHT_WEIGHT",
              "weightLimit": 100,
              "batteryCapacityPercentage": 50.6,
              "state": "IDLE"
          }`
        
       
**3) Get Drone by Status **

    - USAGE: check drone with given status ex: get all IDLE drones
    
    - URL: GET  http://localhost:8085/v1/api/drones/states/IDLE
    
    - Sample Request Body  : -
           
    - Sample Success Response Body : 
          HTTP Code: 200 OK
        ` {
              "drones": [
                  {
                      "serialNumber": "cd-20001",
                      "model": "LIGHT_WEIGHT",
                      "weightLimit": 100.00,
                      "batteryCapacityPercentage": 90.00,
                      "state": "IDLE"
                  },
                  {
                      "serialNumber": "cd-2002344",
                      "model": "LIGHT_WEIGHT",
                      "weightLimit": 100.00,
                      "batteryCapacityPercentage": 50.60,
                      "state": "IDLE"
                  }
              ]
          }`
                    
 **4) Get All Drones **
 
     - USAGE: get all drones with pagination
     
     - URL: GET  http://localhost:8085/v1/api/drones?page=0&size=3
     
     - Sample Request Body  : -
            
     - Sample Success Response Body : 
           HTTP Code: 200 OK
         ` {
               "content": [
                   {
                       "serialNumber": "cd-20001",
                       "model": "LIGHT_WEIGHT",
                       "weightLimit": 100.00,
                       "batteryCapacityPercentage": 90.00,
                       "state": "IDLE"
                   },
                   {
                       "serialNumber": "cd-20002",
                       "model": "MIDDLE_WEIGHT",
                       "weightLimit": 200.00,
                       "batteryCapacityPercentage": 80.00,
                       "state": "LOADING"
                   },
                   {
                       "serialNumber": "cd-20003",
                       "model": "CRUISER_WEIGHT",
                       "weightLimit": 300.50,
                       "batteryCapacityPercentage": 12.00,
                       "state": "LOADED"
                   }
               ],
               "pageNo": 0,
               "pageSize": 3,
               "totalElements": 7,
               "totalPages": 3,
               "last": false
           }`       
      
**5) Add Medicines to a Drone **

    - USAGE: load a drone with medicine
    
    - URL: POST  http://localhost:8085/v1/api/drones/cd-2002344/medications
    
    - Sample Request Body  : 
           {
             "medications": 
                   [
                       {   
                       "code": "MED_1",
                           "name": "Panadol",
                           "weight": 50,
                           "image":  [-119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82, 0, 0, 0, -127, 0, 0, 0, -127, 8, 3, 0, 0, 0, -48, 126, 41, 98, 0, 0, 0, 21, 80, 76, 84, 69, -4, -2, -4, -1, -1, -1, -108, -108, -108, -104, -104, -104, 86, 86, 86, -112, -112, -112, 22, 22, 22, 36, -122, -120, 113, 0, 0, 0, 110, 73, 68, 65, 84, 120, -100, -19, -36, 73, 1, 0, 32, 16, 3, -79, 61, 0, -1, -110, -111, -47, 79, 70, 65, 12, -76, -11, -18, 36, -37, 83, -73, -77, 109, 77, 87, -78, 30, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, -126, -88, 96, -29, -37, -1, -77, -31, -1, -125, 15, 97, 51, 6, 15, 68, -115, -120, 68, 0, 0, 0, 0, 73, 69, 78, 68, -82, 66, 96, -126]
           
                       },
                       {   
                       "code": "MED_2",
                           "name": "Piriton",
                           "weight": 40,
                           "image":  [-119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82, 0, 0, 0, -127, 0, 0, 0, -127, 8, 3, 0, 0, 0, -48, 126, 41, 98, 0, 0, 0, 21, 80, 76, 84, 69, -4, -2, -4, -1, -1, -1, -108, -108, -108, -104, -104, -104, 86, 86, 86, -112, -112, -112, 22, 22, 22, 36, -122, -120, 113, 0, 0, 0, 110, 73, 68, 65, 84, 120, -100, -19, -36, 73, 1, 0, 32, 16, 3, -79, 61, 0, -1, -110, -111, -47, 79, 70, 65, 12, -76, -11, -18, 36, -37, 83, -73, -77, 109, 77, 87, -78, 30, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, -126, -88, 96, -29, -37, -1, -77, -31, -1, -125, 15, 97, 51, 6, 15, 68, -115, -120, 68, 0, 0, 0, 0, 73, 69, 78, 68, -82, 66, 96, -126]
           
                       }
                   ] 
           }

    - Sample Success Response Body : 
          HTTP Code: 200 OK
        ` {
              "medications": [
                  {
                      "id": 3,
                      "code": "MED_1",
                      "name": "Panadol",
                      "weight": 50,
                      "image": "iVBORw0KGgoAAAANSUhEUgAAAIEAAACBCAMAAADQfiliAAAAFVBMVEX8/vz///+UlJSYmJhWVlaQkJAWFhYkhohxAAAAbklEQVR4nO3cSQEAIBADsT0A/5KR0U9GQQy09e4k21O3s21NV7IeAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAoKoYOPb/7Ph/4MPYTMGD0SNiEQAAAAASUVORK5CYII="
                  },
                  {
                      "id": 4,
                      "code": "MED_2",
                      "name": "Piriton",
                      "weight": 40,
                      "image": "iVBORw0KGgoAAAANSUhEUgAAAIEAAACBCAMAAADQfiliAAAAFVBMVEX8/vz///+UlJSYmJhWVlaQkJAWFhYkhohxAAAAbklEQVR4nO3cSQEAIBADsT0A/5KR0U9GQQy09e4k21O3s21NV7IeAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAoKoYOPb/7Ph/4MPYTMGD0SNiEQAAAAASUVORK5CYII="
                  }
              ]
          }`
          
**6) Get Medicines from a Drone **

    - USAGE: get all medicines for a given drone  
    
    - URL: GET  http://localhost:8085/v1/api/drones/cd-2002345/medications
    
    - Sample Request Body  : -
           
    - Sample Success Response Body : 
          HTTP Code: 200 OK
        ` {
              "medications": [
                  {
                      "id": 3,
                      "code": "MED_1",
                      "name": "Panadol",
                      "weight": 50.00,
                      "image": "iVBORw0KGgoAAAANSUhEUgAAAIEAAACBCAMAAADQfiliAAAAFVBMVEX8/vz///+UlJSYmJhWVlaQkJAWFhYkhohxAAAAbklEQVR4nO3cSQEAIBADsT0A/5KR0U9GQQy09e4k21O3s21NV7IeAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAoKoYOPb/7Ph/4MPYTMGD0SNiEQAAAAASUVORK5CYII="
                  },
                  {
                      "id": 4,
                      "code": "MED_2",
                      "name": "Piriton",
                      "weight": 40.00,
                      "image": "iVBORw0KGgoAAAANSUhEUgAAAIEAAACBCAMAAADQfiliAAAAFVBMVEX8/vz///+UlJSYmJhWVlaQkJAWFhYkhohxAAAAbklEQVR4nO3cSQEAIBADsT0A/5KR0U9GQQy09e4k21O3s21NV7IeAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAoKoYOPb/7Ph/4MPYTMGD0SNiEQAAAAASUVORK5CYII="
                  }
              ]
          }`
          
          
 Sample Error Responses:

          HTTP Code: 400 Bad Request
          Response Body:
        ` {
              "code": "ERR-1",
              "message": "Entity not found",
              "developerMessage": "Drone entity not found"
          }`
          
          HTTP Code: 400 Bad Request
          Response Body:
          {
               "code": "ERR-10",
               "message": "Medications are over weighted",
               "developerMessage": "Drone runtime validation failed"
          }
         
