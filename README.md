# Geospatial Data Processing Project

# References
This project was developed as part of the Objektorientierte Programmierung 2 at TU Graz, under the guidance of Houska Peter.

## Contributors
- Faris Mujcinagic [(@fmujcinagic)](https://github.com/fmujcinagic)
- Fedja Mulabegovic [(@fedjamulabegovic)](https://github.com/fedjamulabegovic)
- Milan Mejic

# Overview

Collaborated in a team at the university to develop a **geospatial data processing application**, gaining a solid understanding of Spring Boot and client-server communication.
Key functionalities include **real-time data parsing, spatial data indexing, and the integration of various geospatial databases**, enabling users to perform complex geospatial queries and visualizations. The application's architecture facilitates easy scalability and maintenance, making it an ideal platform for developers and researchers working with geospatial technology. The project provided a hands-on experience with the basics of geospatial data processing, demonstrating the practical application of theoretical concepts in a real-world-like scenario.


# Framework

## Maven

Properly set up Maven dependencies and projects, through which all necessary plugins and build routines should be covered.

## Building using Docker

The provided Docker system allows you to build Backend and Middleware.

First, make sure that you have both docker and docker-compose installed (if you are on Windows, we recommend using WSL2). Afterwards, simply execute the following in your repository root: ```./docker/build_docker.sh && docker-compose up -d```
This should start and run your code in a docker container, including the frontend. You can also omit the -d flag to stay attached to all containers and stop them with ```CTRL+C```.

After starting the container you should be able to access the frontend using  [```localhost:8042```](localhost:8042). Please not that to make the request work within a browser you have to add CORS-headers to the controller methods using the following annotation: ```@CrossOrigin(origins = "*")```

If you want to stop the containers use: ```docker-compose down```

## Demo and Testing with geojson.io
In this project, we utilized GeoJSON, a widely recognized format for encoding a variety of geographic data structures, to validate the precision of mapped coordinates in Graz. This process ensured that all spatial data conformed to real-world geographic positions, enhancing the reliability and usability of our mapping application. By leveraging GeoJSON's capabilities, we were able to efficiently validate and confirm the accuracy of our geospatial information.


## Introduction Screen ![Introduction Screen](/images/grazcoordinates.png)

## API Request Example
```GET http://localhost:8010/amenities?point.x=15.44679&point.y=47.06646&point.d=100&take=2&amenity=restaurant```
```python
{
	"entries": [
		{
			"name": "Athen",
			"id": 291464594,
			"geom": {
				"type": "Point",
				"coordinates": [
					15.446455,
					47.066807
				],
				"crs": {
					"type": "name",
					"properties": {
						"name": "EPSG:0"
					}
				}
			},
			"tags": {
				"note": "Tür zum Raucherbereich stand ständig offen, deshalb 'separated'.",
				"wheelchair": "no",
				"amenity": "restaurant",
				"addr:country": "AT",
				"check_date:opening_hours": "2023-04-26",
				"cuisine": "greek",
				"contact:email": "restaurant.athen1992@gmail.com",
				"addr:postcode": "8010",
				"addr:city": "Graz",
				"diet:vegetarian": "yes",
				"addr:housenumber": "9",
				"contact:phone": "+43 316 816111",
				"indoor_seating": "yes",
				"smoking": "separated",
				"name": "Athen",
				"opening_hours": "Mo-Sa 11:00-24:00; PH,Su 17:00-24:00",
				"addr:street": "Schlögelgasse",
				"outdoor_seating": "no",
				"contact:website": "https://restaurant-athen-graz.eatbu.com/"
			},
			"type": "restaurant"
		},
		{
			"name": "Dim Sum",
			"id": 1618928383,
			"geom": {
				"type": "Point",
				"coordinates": [
					15.447004,
					47.06662
				],
				"crs": {
					"type": "name",
					"properties": {
						"name": "EPSG:0"
					}
				}
			},
			"tags": {
				"amenity": "restaurant",
				"addr:country": "AT",
				"cuisine": "asian",
				"addr:postcode": "8010",
				"takeaway": "yes",
				"addr:city": "Graz",
				"addr:housenumber": "2",
				"indoor_seating": "yes",
				"smoking": "separated",
				"name": "Dim Sum",
				"opening_hours": "Tu-Fr 11:00-15:00,17:00-22:00; Sa,Su 11:00-22:00",
				"addr:street": "Dietrichsteinplatz",
				"outdoor_seating": "no"
			},
			"type": "restaurant"
		}
	],
	"paging": {
		"skip": 0,
		"take": 2,
		"total": 3
	}
}

```
