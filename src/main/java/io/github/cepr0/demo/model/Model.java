package io.github.cepr0.demo.model;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Model extends BaseEntity<Integer> {

	@Column(length = 64)
	private String name;

	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "clob")
	@Type(type = "json")
	private SomeData data;

	public Model(String name, SomeData data) {
		this.name = name;
		this.data = data;
	}

	public String toText() {
		return "Model(id=" + this.getId() + ", name=" + this.getName() + ", data=" + this.getData() + ")";
	}
}
