package FireMock;
import spark.Spark;
import java.util.*;

public class MockServer {
		private final int  port;
		private final List<Mapping> mappings;
		
		 public MockServer(int port) {
			this.port=port;
			this.mappings=MappingLoader.loadMappings("src/main/resources/mappings");
			System.out.println("Loaded " + mappings.size() + " mappings");
			for(Mapping m : mappings) {
			    System.out.println("Mapping URL: " + m.getRequest().getUrl());
			    System.out.println("Mapping method: " + m.getRequest().getMethod());
			    System.out.println("BodyContains: " + m.getRequest().getBodyContains());
			}

		}
		public void start() {
			Spark.port(port);
			
			 Spark.get("/", (req, res) -> "Mock Server is running!");

		        
			Spark.post("/*", (req,res)->{
				
				
				Mapping match=RequestMatcher.findMatch(req, mappings);
				if(match != null) {
					res.status(match.getResponse().getStatus());
					res.type("application/json");
					return match.getResponse().getBody();
				}else {
					res.status(400);
					return "{\"error\":\"No matcching record found\"}";
				}
			});

			Spark.init();
    }
}

