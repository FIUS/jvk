package de.unistuttgart.informatik.fius.jvk;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test public class -- TextureTest.
 */
public class TextureTest {

  @Test
  void testTexture() {
    Texture bluepill = Texture.BLUEPILL;
    assertNotNull(bluepill.getHandle());
  }
}
