package de.lessvoid.nifty.node;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyNodeBuilder;
import de.lessvoid.nifty.spi.node.NiftyNode;

import javax.annotation.Nonnull;

/**
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 */
public final class UniformStackLayoutNode {
  @Nonnull
  private final UniformStackLayoutNodeImpl implementation;

  public UniformStackLayoutNode() {
    this(Orientation.Vertical);
  }

  public UniformStackLayoutNode(@Nonnull final Orientation orientation) {
    implementation = new UniformStackLayoutNodeImpl(orientation);
  }

  @Nonnull
  public Orientation getOrientation() {
    return implementation.getOrientation();
  }

  public void setOrientation(@Nonnull final Orientation orientation) {
    implementation.setOrientation(orientation);
  }

  public void attachNode(@Nonnull final NiftyNodeBuilder builder) {
    // FIXME builder.childNode(implementation);
  }

  public void attachNode(@Nonnull final Nifty nifty, @Nonnull NiftyNode parentNode) {
    // FIXME nifty.node(parentNode, implementation);
  }
}
