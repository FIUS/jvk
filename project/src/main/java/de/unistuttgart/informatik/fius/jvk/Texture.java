/*
 * This source file is part of the FIUS JVK 2020 project.
 * For more information see github.com/FIUS/JVK
 *
 * Copyright (c) 2020 the FIUS JVK 2020 project authors.
 *
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk;

import de.unistuttgart.informatik.fius.icge.simulation.Direction;
import de.unistuttgart.informatik.fius.icge.ui.exception.TextureNotFoundException;
import de.unistuttgart.informatik.fius.icge.ui.TextureRegistry;


/**
 * The enum for all builtin textures of the jvk.
 *
 * @author Tim Neumann
 */
public enum Texture {
    /** The "missing texture" texture */
    MISSING("missing.png"),
    /** The normal wall texture */
    WALL("wall-default.png"),
    /** The normal bush texture */
    BUSH("bush.png"),
    /** The normal coin texture */
    COIN("coin-default.png"),
    /** The normal nut texture */
    NUT("nut.png"),
    /** The normal home_tree texture */
    HOME_TREE("home_tree.png"),
    /** The normal sooty_mans texture */
    SOOTY_MANS("sooty_mans.png"),
    /** The default neo textures */
    NEO("neo/neo-east-0.png", "neo/neo-south-0.png", "neo/neo-west-0.png", "neo/neo-north-0.png"),
    /** the default totoro texture */
    TOTORO("totoro/totoro_east.png", "totoro/totoro_south.png", "totoro/totoro_west.png", "totoro/totoro_north.png"),
    /** the default morpheus texture */
    MORPHEUS(
            "morpheus/morpheus-east-0.png", "morpheus/morpheus-south-0.png", "morpheus/morpheus-west-0.png", "morpheus/morpheus-north-0.png"
    ),
    /** The default mario textures */
    MARIO("mario/mario-east-0.png", "mario/mario-south-0.png", "mario/mario-west-0.png", "mario/mario-north-0.png"),
    /** the default phone booth texture */
    PHONEBOOTH("phone_close.png"),
    /** the ringing phone booth texture */
    PHONEBOOTH_RINGING("phone_open.png"),
    /** red pill texture */
    BLUEPILL("pills/pill_blue.png"),
    /** blue pill texture */
    REDPILL("pills/pill_red.png");

    //for directional textures this is east
    private final TextureInfo info;

    private TextureInfo southInfo = null;

    private TextureInfo westInfo = null;

    private TextureInfo northInfo = null;

    Texture(String textureLocation) {
        this.info = new TextureInfo(textureLocation);
    }

    Texture(String textureEastLocation, String textureSouthLocation, String textureWestLocation, String textureNorthLocation) {
        this.info = new TextureInfo(textureEastLocation);
        this.southInfo = new TextureInfo(textureSouthLocation);
        this.westInfo = new TextureInfo(textureWestLocation);
        this.northInfo = new TextureInfo(textureNorthLocation);
    }

    private void load(TextureRegistry registry, TextureInfo infoToLoad) {
        try {
            infoToLoad.textureHandle = registry
                    .loadTextureFromResource("textures/" + infoToLoad.textureLocation, Texture.class::getResourceAsStream);
        } catch (TextureNotFoundException e) {
            System.out.println("Could not find texture: " + infoToLoad.textureLocation);
            e.printStackTrace();
            infoToLoad.textureHandle = registry
                    .loadTextureFromResource("textures/" + MISSING.info.textureLocation, Texture.class::getResourceAsStream);
        }
    }

    /**
     * Load this texture with the given registry
     *
     * @param registry
     *     The registry to load the texture with.
     * @throws TextureNotFoundException
     *     if neither the correct nor the "missing texture" texture can be found
     */
    public void load(TextureRegistry registry) {
        load(registry, this.info);
        if (this.southInfo != null) load(registry, this.southInfo);
        if (this.westInfo != null) load(registry, this.westInfo);
        if (this.northInfo != null) load(registry, this.northInfo);
    }

    /**
     * Get the handle of this texture.
     * <p>
     * The texture must be loaded for this. ({@link #load(TextureRegistry)} needs to have been called before)
     * </p>
     *
     * @return the texture handle of this
     * @throws IllegalStateException
     *     if the texture was not loaded before.
     */
    public String getHandle() {
        if (this.info.textureHandle == null) throw new IllegalStateException("Need to be loaded first");
        return this.info.textureHandle;
    }

    /**
     * Get the handle of this texture for the given direction.
     * <p>
     * The texture must be loaded for this. ({@link #load(TextureRegistry)} needs to have been called before)
     * </p>
     * <p>
     * If no texture is available for this direction, the default texture is returned.
     * </p>
     *
     * @param direction
     *     the to get the handle for.
     * @return the correct texture handle
     * @throws IllegalStateException
     *     if the texture was not loaded before.
     */
    public String getHandle(Direction direction) {
        if (this.info.textureHandle == null) throw new IllegalStateException("Need to be loaded first");
        switch (direction) {
            case EAST:
                break;
            case SOUTH:
                if (this.southInfo != null) return this.southInfo.textureHandle;
                break;
            case WEST:
                if (this.westInfo != null) return this.westInfo.textureHandle;
                break;
            case NORTH:
                if (this.northInfo != null) return this.northInfo.textureHandle;
                break;
            default:
                break;
        }
        return this.info.textureHandle;
    }

    private class TextureInfo {
        final String textureLocation;
        String       textureHandle;

        TextureInfo(String location) {
            this.textureLocation = location;
        }
    }
}
