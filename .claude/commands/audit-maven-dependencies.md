Your goal is to audit dependencies from pom.xml


Do the following:
1. Run `mvn versions:display-dependency-updates`
2. Run `mvn versions:use-latest-releases`
3. Don't use beta or snapshot versions.
4. Update needed to their latest compatible vervions.
5. Run tests to ensure nothing broke.
