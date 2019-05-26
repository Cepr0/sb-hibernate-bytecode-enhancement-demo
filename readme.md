Example of using [Hibernate Bytecode Enhancement][1] to partially load an entity data 
utilizing `@Basic` annotation with `FetchType.LAZY`:

```java
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
}
```

To see the effect - start the app and see the log:

```bash
mvn spring-boot:run
```

[1]: https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#BytecodeEnhancement