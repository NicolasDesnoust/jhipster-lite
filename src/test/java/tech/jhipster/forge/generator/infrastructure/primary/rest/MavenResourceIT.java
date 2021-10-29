package tech.jhipster.forge.generator.infrastructure.primary.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tech.jhipster.forge.TestUtils.assertFileContent;
import static tech.jhipster.forge.generator.application.MavenAssertFiles.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.forge.IntegrationTest;
import tech.jhipster.forge.TestUtils;
import tech.jhipster.forge.error.domain.GeneratorException;
import tech.jhipster.forge.generator.domain.core.FileUtils;
import tech.jhipster.forge.generator.domain.core.Project;
import tech.jhipster.forge.generator.infrastructure.primary.dto.ProjectDTO;

@IntegrationTest
@AutoConfigureMockMvc
class MavenResourceIT {

  @Autowired
  MockMvc mockMvc;

  @Test
  void shouldInit() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/maven.json", ProjectDTO.class);
    if (projectDTO == null) {
      throw new GeneratorException("Error when reading file");
    }
    projectDTO.path(FileUtils.tmpDirForTest());

    mockMvc
      .perform(
        post("/api/build-tools/maven").contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    Project project = ProjectDTO.toProject(projectDTO);
    assertFilesMaven(project);
    assertFileContent(project, "pom.xml", List.of("<groupId>tech.jhipster.chips</groupId>", "<artifactId>chips</artifactId>"));
  }

  @Test
  void shouldAddPomXml() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/maven.json", ProjectDTO.class);
    if (projectDTO == null) {
      throw new GeneratorException("Error when reading file");
    }
    projectDTO.path(FileUtils.tmpDirForTest());

    mockMvc
      .perform(
        post("/api/build-tools/maven/pom-xml")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    Project project = ProjectDTO.toProject(projectDTO);
    assertFilesPomXml(project);
    assertFileContent(project, "pom.xml", List.of("<groupId>tech.jhipster.chips</groupId>", "<artifactId>chips</artifactId>"));
  }

  @Test
  void shouldAddMavenWrapper() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/maven.json", ProjectDTO.class);
    if (projectDTO == null) {
      throw new GeneratorException("Error when reading file");
    }
    projectDTO.path(FileUtils.tmpDirForTest());

    mockMvc
      .perform(
        post("/api/build-tools/maven/wrapper")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    Project project = ProjectDTO.toProject(projectDTO);
    assertFilesMavenWrapper(project);
  }
}