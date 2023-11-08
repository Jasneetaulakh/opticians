# Jasneet's Opticians System

---

## Description


This is a personal side project. I wanted to practice creating a backend application and decided to create something I am familiar with. 
Its a work in progress and is continually being worked on.


| Booking | Description |
| -------------- | ----------- |
| `ID` | Each booking has a unique identifier |
| `Date` | Date of the appointment |
| `Time Slot` | Time slot of the appointment |
| `Customer Id` | Each booking must be connected to a customer |


| Customer | Description |
| -------------- | ----------- |
| `ID` | Each customer has a unique identifier |
| `Name` | A customer must have a name, this cannot be blank or null |
| `Date of Birth` | A customer must have a DoB, this cannot be in the past and cannot be null |
| `Postcode` | Ability to add postcode |
| `Door number` | Ability to add door number to complete customer's address |
| `Phone number` | Ability to add phone number |


| Professional Staff | Description |
| -------------- | ----------- |
| `ID` | Each professional staff (PS) member has a unique identifier |
| `Staff Pin` | Each PS must have a pin which cannot be null and must be unique |
| `GOC Number` | Each PS must have a GOC No which cannot be null and is unique by default |
| `Name` | A PS must have a name, this cannot be blank or null |


| Examination | Description |
| -------------- | ----------- |
| `ID` | Each examination has a unique identifier |
| `Date` | The date on which the examination took place |
| `History` | History of the patient, LEE, RFV, POH, GH, MEDS, FGH/FOH... |
| `Internal Eye Examination` | A record of the internal eye exam (lens / vitreous / fundus) |
| `External Eye Examination` | A record of the external eye exam (lids + lashes / conjunctiva / tear film) |
| `Management` | A record of how this customer will be managed |
| `Recall` | When should the patient return for another eye exam (24/12, 12/12 ..) |
| `Customer ID` | Each examination must be connected to a customer |
| `PS ID` | Each examination must be connected to the PS member who carried out the examination |

---

## Uses

The system currently allows you to:
* create / update / delete / find a/all customer profile(s)
* create / update / delete / find a/all professional staff member(s)
* create / delete an appointment booking for a customer
* input details of an examination for a customer
* delete / find an examination / all examinations
* find an appointment booking by:
  - `booking id`
  - `customer id` - returns list of past/future appointments
  - `date` - returns list of appointments for given date
  - `time slot` - returns list of appointments for given time slot  
* find an examination by:
  - `examination id`
  - `customer id` - returns list of customer's examinations
  - `professional staff id` - returns list of examinations performed by PS member  
    
---
## Requirements

To run the application use the maven command below.

>mvn clean spring-boot:run

At the moment, the data is temporarily stored using a h2 database which can be viewed on localhost:8080/h2

JDBC URL found [here](/Users/jasneetaulakh/IdeaProjects/opticians 2/src/main/resources/application.properties)

The URL's for the requests can be found below, I use postman for now

* [Booking URL's](/Users/jasneetaulakh/IdeaProjects/opticians 2/src/main/java/com/example/demo/web/BookingController.java)
* [Customer URL's](/Users/jasneetaulakh/IdeaProjects/opticians 2/src/main/java/com/example/demo/web/CustomerController.java)
* [Professional Staff URL's](/Users/jasneetaulakh/IdeaProjects/opticians 2/src/main/java/com/example/demo/web/ProfessionalStaffController.java)
* [Examination URL's](/Users/jasneetaulakh/IdeaProjects/opticians 2/src/main/java/com/example/demo/web/ExaminationController.java)