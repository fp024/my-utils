package org.fp024.util.vault;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Properties;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

class VaultClientTest {

  @Test
  void test() throws IOException {

    Properties prop =
        PropertiesLoaderUtils.loadProperties(new ClassPathResource("vault.properties"));
    Properties tokenProp =
        PropertiesLoaderUtils.loadProperties(new ClassPathResource("vault-token.properties"));

    VaultConfig config =
        new VaultConfig(
            prop.getProperty("vault.server.url"), //
            tokenProp.getProperty("vault.policy.token"),
            prop.getProperty("vault.root.path"),
            prop.getProperty("vault.project.path"));

    VaultClient client = new VaultClient(config);

    String username = client.get("db.username");
    String password = client.get("db.password");

    assertThat(username).isEqualTo("db_username");
    assertThat(password).isEqualTo("db_password");

    // long, int 값을 처리하고 싶어서 아래의 값을 미리 Vault 서버에 넣어놓고 테스트를 수행했다.
    int intValue = client.getInt("int.value");
    long longValue = client.getLong("long.value");

    assertThat(intValue).isEqualTo(1);
    assertThat(longValue).isEqualTo(2147483648L);
  }

  @Test
  void testDefault() {

    VaultClient client = new VaultClient();

    String username = client.get("db.username");
    String password = client.get("db.password");

    assertThat(username).isEqualTo("db_username");
    assertThat(password).isEqualTo("db_password");
  }
}
