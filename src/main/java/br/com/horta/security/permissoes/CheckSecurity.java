package br.com.horta.security.permissoes;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	public @interface Usuario {

		// DH01
		//Adminitrador pode tudo
		//Logado pode o POST,PUT,GET(ID) e DELETE
		//NaoLogado pode o POST
		@PreAuthorize("isAuthenticated() and " + 
				"hasAuthority('Admin')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface Administrador {
		}

		// DH02
		@PreAuthorize("isAuthenticated() and " + 
				"hasAuthority('cadastrado')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface Logado {
		}	
		
		// DH03
		@PreAuthorize("isAuthenticated() and " + 
				"hasAuthority('DH03')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface NaoLogado {
		}
	}
	
	
	public @interface Planta {
		//Adminitrador pode tudo
		//Logado pode o GET(ID) e GET
		//NaoLogado pode o GET(ID) e GET
		
		
		// DH01
		@PreAuthorize("isAuthenticated() and " + 
				"hasAuthority('Admin')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface Administrador {
		}	
		
		// DH02
		@PreAuthorize("isAuthenticated() and " + 
				"hasAuthority('cadastrado')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface Logado {
		}	
		
		// DH03
		@PreAuthorize("isAuthenticated() and " + 
				"hasAuthority('DH03')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface NaoLogado {
		}	
	}
	
}