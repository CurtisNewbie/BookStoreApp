# DEMO JSON Data

You can use these data to send HTTP requests to the backend server.

## Create and Update Books

POST or PUT http://localhost:8080/api/book

To update a book, you must have it in the database beforehand.

    {
        "id": "1",
        "title": "The Animator's Survival Kit",
        "author": "Richard E. Williams",
        "content" : "In this book, based on his sold-out Animation Masterclass in the United States and across Europe, Williams provides the underlying principles of animation that very animator - from beginner to expert, classic animator to computer animation whiz - needs.",
        "price": 21.95,
        "date" : "2009-11-05",
        "img" : "https://images-na.ssl-images-amazon.com/images/I/51YvgAKQYRL._SX421_BO1,204,203,200_.jpg"
    }

    {
        "id": "3",
        "title": "Storyboard Notebook 4:3 Panels",
        "author": "Simple Storyboards",
            "content" : "By the end of the storyboarding notebook you will be able to go through your storyboard writing examples and track your progress and you will have a record of what works best for you.",
        "price": 6.78,
        "date" : "2020-02-01",
        "img" : "https://images-na.ssl-images-amazon.com/images/I/51vJRPD1sxL._SX385_BO1,204,203,200_.jpg"
    }

## Create and Update News

POST or PUT http://localhost:8080/api/new

    {
        "title":"Sale!!!!!!",
        "content" : "All books are for free!!!!!!! Just pick one and add them to your cart!!!!!",
        "date": "2020-02-08"
    }

## Create or Update DeliveryOption

POST or PUT http://localhost:8080/api/delivery/option

    {
        "name": "Next Day Delivery (Order before 2pm)",
        "price": 5
    }

    {
        "name": "3 - 5 Days Delivery",
        "price": 3.2
    }

    {
        "name": "One Week Delivery",
        "price": 2.6
    }

## Create or Update Order

POST or PUT http://localhost:8080/api/order

Make sure the `book with id = 1` is created before sending this request, and change the `"id"` in `"deliveryOption"` to one that exists in the database.

    {
        "address": {
            "city": "Sheffield",
            "county": "Yorkshire",
            "firstLine": "Some Randome House",
            "secondLine": "1st street",
            "postCode" : "SH1 1HS"
        },
        "booksOnOrder": [
            {
            "amount": 10,
                "book": {
                    "id": 1
                }
            }
        ],
        "firstName": "CurtisNewbie",
        "lastName": "Z",
        "deliveryOption": {
            "id" : 1
        }
    }
