package kr.co.hi_story.util.jwt;
import java.util.Map;

public interface JwtService {
	public <T> String create(String key,T data,String subject);
	public byte[] generateKey();
	boolean isUsable(String jwt);
	public Map<String, Object> get(String key);
	public String getUserID();
	public int logout(String key);
}