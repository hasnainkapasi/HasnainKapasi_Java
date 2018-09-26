# ABC investment general ledger system
This application calculates end of day position of stock accounts based on transactions conducted on a day.

It reads start of day position file & day transactions file, based on these two inputs it calculates end of day position of stock accounts & generates EOD position file.

It also displays (in console) name of the instrument with the largest and lowest net transaction volumes for the day.

# Software required

1. JDK 8
2. Maven

# Steps to run:

1. Run Maven build by running below command:

	mvn clean install
	
2. To calculate EOD positions execute below command:

	java -jar \<path of jar\> \<position file path\> \<transaction file path\> \<output file folder\>

	Example:
	
	java -jar c:/jars/general-ledger-system.jar c:/inputFiles/sod_positions.txt c:/inputfiles/transactions.txt c:/output

3. check output folder for Output_EndOfDay_Positions.txt file.
