package mvc;

import org.apache.commons.dbcp2.BasicDataSource;

public class SPool {
	public BasicDataSource DataSource;

	public SPool() {
		Init();
	}
	
	private void Init(){
		DataSource = new BasicDataSource();
		DataSource.setDriverClassName(MyProperties.getKey("controlador"));
		DataSource.setUsername(MyProperties.getKey("usuario"));
		DataSource.setPassword(MyProperties.getKey("clave"));
		DataSource.setUrl(MyProperties.getKey("servidor")+MyProperties.getKey("bd"));
		DataSource.setMaxTotal(25);
		DataSource.setTimeBetweenEvictionRunsMillis(1000);
	}
}
