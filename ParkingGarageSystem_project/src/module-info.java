/**
 * 
 */
/**
 * 
 */
module ParkingGarageSystem_project {
	requires java.desktop;
}

//  FileIO:
// 		Idea of Serialization: write an object to disk and read an object from disk
// 			   	-> Automobile will be a utilized as a collection of objects. 
//  File contains:
//   	- first row has name of the model-(comma)-base price
//   	- other rows have the first column as the name of the Configuration
//  		-> column in [1, ..., n] have name of option-(comma)-price of option    

//public Automobile readFile(String filename, Automobile autoObj) throws FileNotFoundException{
//
//	filename = setAutoFile(filename);
//	try {
//		
//			BufferedReader buff = new BufferedReader(new FileReader(filename));
//			String line = buff.readLine();
//			boolean hasLines;
//			
//			String [] strOptionSet;
//			int indexSet = 0;
//
//			if (( hasLines = (line != null) ? true : false )) {
//				// initial condition to establish "what car" - identify and associate the vehicle
//				// modelInfo contains := [0] -> modelName, [1] -> basePrice
//				// make and model name split by space
//		   
//				String [] modelInfo = line.split(",");              
//				String [] make_model = modelInfo[0].split(" ");  
//				
//				// make is the first element so take model into its own string array
//				autoObj.setMake_Model(make_model[0], String.join(" ", 
//															Arrays.copyOfRange(make_model, 1, make_model.length)));
//				autoObj.setBasePrice(Float.valueOf(modelInfo[1]));
//			}
//			
//			while (hasLines) {
//				// iterate until EOF. rows 1 ... N has
//				//     col 0 : name of OptionSet  ^  col x in {1,..,N} : option values
//				line = buff.readLine();
//
//				if (line == null || line.trim().isEmpty())
//					hasLines = false;
//				else {
//					
//					strOptionSet = line.split("\t");           
//			    	autoObj.setOptionSet(strOptionSet[0], strOptionSet.length-1);
//			    	
//			    	for (int i = 1; i < strOptionSet.length; i++)
//			    		autoObj.setNewOptionValues(strOptionSet[i].split(","), indexSet, i-1);
//			        indexSet++;
//				}
//			}
//			buff.close();		
//
//		} catch (IOException e)	{	
//				throw new RuntimeException(e);
//		} catch (AutoException autoE) {
//				autoE.printmyproblem();
//				System.exit(1);
//		}
//    return autoObj;
//}
//