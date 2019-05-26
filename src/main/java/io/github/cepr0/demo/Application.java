package io.github.cepr0.demo;

import io.github.cepr0.demo.model.Model;
import io.github.cepr0.demo.model.SomeData;
import io.github.cepr0.demo.repo.ModelRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootApplication
public class Application {

	private final ModelRepo repo;

	public Application(final ModelRepo repo) {
		this.repo = repo;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Order(Ordered.HIGHEST_PRECEDENCE)
	@EventListener
	public void insertData(ApplicationReadyEvent e) {
		log.info("[i] Insert a Model");
		repo.save(new Model("model", null));
	}

	@Order(Ordered.HIGHEST_PRECEDENCE + 1)
	@Transactional
	@EventListener
	public void updateData(ApplicationReadyEvent e) {
		log.info("[i] Update the Model");
		repo.findById(1)
				.map(model -> model.setData(new SomeData(1, "text")));
	}

	@Order(Ordered.HIGHEST_PRECEDENCE + 2)
	@Transactional(readOnly = true)
	@EventListener
	public void readName(ApplicationReadyEvent e) {
		log.info("[i] Read a Model without its data");
		repo.findById(1)
				.ifPresent(model -> log.info("[i] Model name is '{}'", model.getName()));
	}

	@Order(Ordered.HIGHEST_PRECEDENCE + 3)
	@Transactional(readOnly = true)
	@EventListener
	public void readData(ApplicationReadyEvent e) {
		log.info("[i] Read the Model data only");
		repo.getDataById(1)
				.ifPresent(prj -> log.info("[i] Model data is '{}'", prj.getData()));
	}

	@Order(Ordered.HIGHEST_PRECEDENCE + 4)
	@Transactional(readOnly = true)
	@EventListener
	public void readFullModel(ApplicationReadyEvent e) {
		log.info("[i] Read a full Model");
		repo.getFullById(1)
				.ifPresent(model -> log.info("[i] Model is '{}'", model.toText()));
	}
}
