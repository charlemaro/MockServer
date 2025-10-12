package FireMock;
import java.util.List;
import java.util.Objects;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
public class MappingLoader {
	public static List<Mapping> loadMappings(String dirPath){
		List<Mapping>mappings=new ArrayList<Mapping>();
		
		File dir=new File(dirPath);
		Gson gson=new Gson();
		if(!dir.exists() || !dir.isDirectory()) {
			return mappings;
		}
		
		for(File file:Objects.requireNonNull(dir.listFiles())) {
			try(FileReader reader=new FileReader(file)){
				Mapping mapping=gson.fromJson(reader,Mapping.class);
				mappings.add(mapping);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mappings;
	}

}
