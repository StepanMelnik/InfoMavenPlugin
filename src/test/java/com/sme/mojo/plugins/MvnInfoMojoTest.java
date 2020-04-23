package com.sme.mojo.plugins;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Unit tests of {@link MvnInfoMojo}.
 */
public class MvnInfoMojoTest
{
    @Mock
    private Log log;

    @Mock
    private MavenProject project;

    @Mock
    private Artifact artifact;

    private final Map<String, MavenProject> mapPluginContext = new HashMap<>();
    private MvnInfoMojo mvnInfoMojo;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);

        mockWhens();

        mapPluginContext.put("project", project);

        mvnInfoMojo = new MvnInfoMojo();
        mvnInfoMojo.setLog(log);
        mvnInfoMojo.setPluginContext(mapPluginContext);
    }

    private void mockWhens()
    {
        when(artifact.toString()).thenReturn("parent artifcat");

        when(project.toString()).thenReturn("artifact info");
        when(project.getDefaultGoal()).thenReturn("goal");
        when(project.getDescription()).thenReturn("description");
        when(project.getName()).thenReturn("name");
        when(project.getPackaging()).thenReturn("packaging");
        when(project.getUrl()).thenReturn("url");
        when(project.getBasedir()).thenReturn(new File("."));
        when(project.getParentArtifact()).thenReturn(artifact);
        when(project.getModules()).thenReturn(Arrays.asList(""));
    }

    @Test
    void testInfoMojo() throws Exception
    {
        mvnInfoMojo.execute();

        verify(log, times(9)).info(anyString());

        verify(log, times(1)).info("artifact info");
        verify(log, times(1)).info("DefaultGoal = goal");
        verify(log, times(1)).info("Description = description");
        verify(log, times(1)).info("Name = name");
        verify(log, times(1)).info("Packaging = packaging");
        verify(log, times(1)).info("Url = url");
        verify(log, times(1)).info("ParentArtifact = parent artifcat");
        verify(log, times(1)).info("Modules = []");
    }
}
