package com.sme.mojo.plugins;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

/**
 * Goal which prepares info about the build.
 * <p>
 * Fetch and prints info about the project from maven context.
 * </p>
 */
@Mojo(name = "info", defaultPhase = LifecyclePhase.COMPILE, requiresDependencyResolution = ResolutionScope.RUNTIME)
public class MvnInfoMojo extends AbstractMojo
{
    /**
     * Disables the plugin execution.
     */
    @Parameter(property = "skip", defaultValue = "false")
    private boolean skip;

    /**
     * Fail the plugin execution on error or not.
     */
    @Parameter(property = "failOnError", defaultValue = "true")
    private boolean failOnError;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException
    {
        Log log = getLog();

        if (skip)
        {
            log.info("Skip to get properties about the project.");
            return;
        }

        MavenProject project = (MavenProject) getPluginContext().get("project");
        log.info(project.toString());
        log.info("DefaultGoal = " + project.getDefaultGoal());
        log.info("Description = " + project.getDescription());
        log.info("Name = " + project.getName());
        log.info("Packaging = " + project.getPackaging());
        log.info("Url = " + project.getUrl());
        log.info("BaseDir = " + project.getBasedir());
        log.info("ParentArtifact = " + project.getParentArtifact());
        log.info("Modules = " + project.getModules());
    }
}
