# Book-Consultant
## Overview
Create the backend APIs of the admin dashboard a Web app (that allows users to find and
book sessions with consultants).The admin dashboard is the key component of any web
app. So, that API should be very flexible to be integrated with any other component.
The admin dashboard approves the consultants account to verified status. It also keeps a
track of the no of consultants registered, no of clients registered and no of sessions booked.

## Setup Instructions
To set up the project locally, follow these steps:

1. Clone the repository to your local machine:
2. Install dependencies:
3. Start the server:
4. The server should now be running locally. You can access the API at `http://localhost:8080/admin`

## API Usage
Below are the endpoints available in this API and their respective functionalities:

### Endpoint 1
**Description:** getConsultantRequests \
**Method:** GET\
**URL:** `/consultant-requests`

### Endpoint 2
**Description:** approve Consultant Request \
**Method:** POST \
**URL:** `/approve-consultant-request/{requestId}`

### Endpoint 3
**Description:** add Consultant Details \
**Method:** POST \
**URL:** `/add-consultant-details/{mentorId}`

### Endpoint 4
**Description:** get All Consultants \
**Method:** GET \
**URL:** `/consultants`

### Endpoint 5
**Description:** get Consultant Details By Name \
**Method:** GET \
**URL:** `/consultant-details/name`

### Endpoint 6
**Description:** get Consultant Details By Job Role \
**Method:** GET \
**URL:** `/consultant-details/role`

### Endpoint 7
**Description:** get web app statistics \
**Method:** GET \
**URL:** `/web-app-statistics`
  
