# InfoMavenPlugin

Simple maven plugin to print all maven properties.

## Description

The plugin prints maven project properties.

### Build

> mvn clean install

### Usage
> mvn com.sme.mojo:maven-info-plugin:0.0.0.Dev-SNAPSHOT:info

> mvn com.sme.mojo:maven-info-plugin:info

> mvn info:info

Use the following configuration to execute the plugin in maven build on a specific stage:

```xml
    <build>
        <plugins>
				...
            <!-- Run info:info MOJO plugin in "compile" phase -->
            <plugin>
                <groupId>com.sme.mojo</groupId>
                <artifactId>info-maven-plugin</artifactId>
                <version>0.0.0.Dev-SNAPSHOT</version>
                <configuration>
                    <skip>false</skip>
                </configuration>
                <executions>
                    <execution>
                        <phase>compile</phase>
                        <goals>
                            <goal>info</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

```

