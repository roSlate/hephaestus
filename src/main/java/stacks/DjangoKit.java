package stacks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class DjangoKit implements StackKit {

    @Override
    public void generateBlueprint(Path targetRoot, String projectName) throws IOException {
        System.out.println("🐍 Injecting modular Django template blueprint...");

        String packageName = projectName.replaceAll("[^a-zA-Z0-9_]", "_");
        Path settingsDir = targetRoot.resolve(packageName);
        Files.createDirectories(settingsDir);

        Files.writeString(settingsDir.resolve("__init__.py"), "",
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        String settingsContent = """
                SECRET_KEY = "django-insecure-change-me"
                DEBUG = True
                ALLOWED_HOSTS = []
                
                INSTALLED_APPS = [
                    "django.contrib.admin",
                    "django.contrib.auth",
                    "django.contrib.contenttypes",
                    "django.contrib.sessions",
                    "django.contrib.messages",
                    "django.contrib.staticfiles",
                ]
                
                MIDDLEWARE = [
                    "django.middleware.security.SecurityMiddleware",
                    "django.contrib.sessions.middleware.SessionMiddleware",
                    "django.middleware.common.CommonMiddleware",
                    "django.middleware.csrf.CsrfViewMiddleware",
                    "django.contrib.auth.middleware.AuthenticationMiddleware",
                    "django.contrib.messages.middleware.MessageMiddleware",
                    "django.middleware.clickjacking.XFrameOptionsMiddleware",
                ]
                
                ROOT_URLCONF = "%s.urls"
                
                TEMPLATES = [
                    {
                        "BACKEND": "django.template.backends.django.DjangoTemplates",
                        "DIRS": [],
                        "APP_DIRS": True,
                        "OPTIONS": {
                            "context_processors": [
                                "django.template.context_processors.debug",
                                "django.template.context_processors.request",
                                "django.contrib.auth.context_processors.auth",
                                "django.contrib.messages.context_processors.messages",
                            ],
                        },
                    },
                ]
                
                WSGI_APPLICATION = "%s.wsgi.application"
                
                DATABASES = {
                    "default": {
                        "ENGINE": "django.db.backends.sqlite3",
                        "NAME": "db.sqlite3",
                    }
                }
                
                STATIC_URL = "static/"
                
                DEFAULT_AUTO_FIELD = "django.db.models.BigAutoField"
                """.formatted(packageName, packageName);

        Files.writeString(settingsDir.resolve("settings.py"), settingsContent,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        String urlsContent = """
                from django.contrib import admin
                from django.urls import path
                
                urlpatterns = [
                    path("admin/", admin.site.urls),
                ]
                """;
        Files.writeString(settingsDir.resolve("urls.py"), urlsContent,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        String wsgiContent = """
                import os
                from django.core.wsgi import get_wsgi_application
                
                os.environ.setdefault("DJANGO_SETTINGS_MODULE", "%s.settings")
                
                application = get_wsgi_application()
                """.formatted(packageName);
        Files.writeString(settingsDir.resolve("wsgi.py"), wsgiContent,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        String manageContent = """
                #!/usr/bin/env python
                import os
                import sys
                
                
                def main():
                    os.environ.setdefault("DJANGO_SETTINGS_MODULE", "%s.settings")
                    try:
                        from django.core.management import execute_from_command_line
                    except ImportError as exc:
                        raise ImportError(
                            "Couldn't import Django. Are you sure it's installed and "
                            "available on your PYTHONPATH environment variable?"
                        ) from exc
                    execute_from_command_line(sys.argv)
                
                
                if __name__ == "__main__":
                    main()
                """.formatted(packageName);
        Files.writeString(targetRoot.resolve("manage.py"), manageContent,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        Files.writeString(targetRoot.resolve("requirements.txt"), "Django>=5.0,<6.0\n",
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}