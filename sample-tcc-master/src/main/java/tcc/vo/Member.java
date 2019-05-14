package tcc.vo;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Member {

    private String id;
    private String name;
    
    public Member() {
    	super();
    }
    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }
    
    public String getName() {
    	return name;
    }
    
    public static Member deserializeJSON(final String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        System.out.println(json);
        return objectMapper.readValue(json, Member.class);
        
    }

    public String serializeJSON() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

   
}
