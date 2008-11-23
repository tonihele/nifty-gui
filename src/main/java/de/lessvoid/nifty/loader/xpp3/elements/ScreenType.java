package de.lessvoid.nifty.loader.xpp3.elements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.loader.xpp3.Attributes;
import de.lessvoid.nifty.loader.xpp3.ClassHelper;
import de.lessvoid.nifty.loader.xpp3.elements.helper.NiftyCreator;
import de.lessvoid.nifty.loader.xpp3.elements.helper.StyleHandler;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.tools.TimeProvider;

/**
 * ScreenType.
 * @author void
 */
public class ScreenType {

  /**
   * screen id.
   * @required
   */
  private String id;

  /**
   * controller.
   * @required
   */
  private String controller;

  /**
   * effectGroup.
   * @optional
   */
  private String effectGroup;

  /**
   * layerGroup.
   * @optional
   */
  private Collection < ScreenTypeLayerGroupType > layerGroups = new ArrayList < ScreenTypeLayerGroupType >();

  /**
   * layer.
   * @required
   */
  private Collection < LayerType > layers = new ArrayList < LayerType >();

  /**
   * @param idParam id
   * @param controllerParam controller
   */
  public ScreenType(final String idParam, final String controllerParam) {
    this.id = idParam;
    this.controller = controllerParam;
  }

  /**
   * set id.
   * @param idParam id
   */
  public void setId(final String idParam) {
    this.id = idParam;
  }

  /**
   * setController.
   * @param controllerParam controller
   */
  public void setController(final String controllerParam) {
    this.controller = controllerParam;
  }

  /**
   * setEffectGroup.
   * @param effectGroupParam effectGroup
   */
  public void setEffectGroup(final String effectGroupParam) {
    this.effectGroup = effectGroupParam;
  }

  /**
   * addLayerGroup.
   * @param layerGroupParam layerGroup
   */
  public void addLayerGroup(final ScreenTypeLayerGroupType layerGroupParam) {
    layerGroups.add(layerGroupParam);
  }

  /**
   * addLayer.
   * @param layerParam layer
   */
  public void addLayer(final LayerType layerParam) {
    layers.add(layerParam);
  }

  /**
   * create screen.
   * @param nifty nifty
   * @param timeProvider time
   * @param registeredEffects effects
   * @param registeredControls registeredControls
   * @param styleHandler styleHandler
   * @return Screen
   */
  public Screen createScreen(
      final Nifty nifty,
      final TimeProvider timeProvider,
      final Map < String, RegisterEffectType > registeredEffects,
      final Map < String, RegisterControlDefinitionType > registeredControls,
      final StyleHandler styleHandler) {
    ScreenController screenController = nifty.findScreenController(controller);
    if (screenController == null) {
      screenController = ClassHelper.getScreenController(controller);
    }
    Screen screen = new Screen(
        nifty,
        id,
        screenController,
        timeProvider);
    Element rootElement = NiftyCreator.createLayer(
        null,
        null,
        "root",
        nifty,
        screen,
        new AttributesType(),
        timeProvider);
    screen.setRootElement(rootElement);
    for (LayerType layerType : layers) {
      screen.addLayerElement(
          layerType.createElement(
              rootElement,
              nifty,
              screen,
              registeredEffects,
              registeredControls,
              styleHandler,
              timeProvider,
              null,
              screenController));
    }
    screen.processAddAndRemoveLayerElements();
    return screen;
  }
}
