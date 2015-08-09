package personal.deon.framework.core.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;


@MappedSuperclass
public abstract class  AbsEntity {
	protected String id;
	
	@Id
    @Column(name = "id", unique = true, nullable = false, length = 32)
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator="idGenerator")
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
}