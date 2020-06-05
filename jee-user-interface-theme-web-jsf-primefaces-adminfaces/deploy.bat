mvn -N -P cyk.repo.repsy.io deploy:deploy-file -Dpackaging=jar -Dfile="target/jee-user-interface-theme-web-jsf-primefaces-adminfaces-0.1.0.jar" ^
-DrepositoryId=cyk.repo.repsy.io -Durl=https://repo.repsy.io/mvn/kycdev/default ^
-DgroupId=org.cyk.jee.user.interface.theme.web.jsf.primefaces.adminfaces ^
-DartifactId=jee-user-interface-theme-web-jsf-primefaces-adminfaces ^
-Dversion=0.1.0