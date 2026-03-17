FROM amazoncorretto:21-alpine
#Expose application port
EXPOSE 8080
#Move to the working directory
WORKDIR /app
#Copy the final application artifact
COPY target/azure-*.jar ./azure-learning.jar
#Execute the application
CMD ["java", "-jar", "/app/azure-learning.jar"]
