@ImageInjectionSample
Feature: Sample Camera App - Image Injection Sample
  
  @AndroidImageInjectionSample
Scenario: Android Sample Image Injection on camera App
	Given I start application by id "com.perfectomobile.cameraapp"
	And I give permissions to the camera if asked
	When I start inject "PUBLIC:Testing.png" image to application id "com.perfectomobile.cameraapp"
	And I click on text "PICTURE"
	Then I must see text "Testing"
	And I stop image injection
	
	
	
	
	  @IOSImageInjectionSample
Scenario: IOS Sample Image Injection on camera App
	Given I start application by id "Perfecto.CameraTestApp"
	And I give permissions to the camera if asked
	When I start inject "PUBLIC:Testing.png" image to application id "Perfecto.CameraTestApp"
	And I click on "//*[@label='Video']"
	Then I must see text "Testing"
	And I stop image injection