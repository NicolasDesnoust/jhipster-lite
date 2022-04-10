package tech.jhipster.lite.generator.server.springboot.webflux.web.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.webflux.web.domain.SpringBootWebfluxService;

@Service
public class SpringBootWebfluxApplicationService {

  private final SpringBootWebfluxService springBootWebfluxService;

  public SpringBootWebfluxApplicationService(SpringBootWebfluxService springBootWebfluxService) {
    this.springBootWebfluxService = springBootWebfluxService;
  }

  public void addSpringBootWebflux(Project project) {
    this.springBootWebfluxService.addSpringBootWebflux(project);
  }
}