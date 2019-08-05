package bleach.hack.gui.clickgui;

import bleach.hack.BleachHack;
import bleach.hack.module.Category;
import bleach.hack.module.ModuleManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;

public class ClickGuiScreen extends Screen {
	private int len;
	private int setLen;
	
	private boolean lMousePressed = false;
	private boolean rMousePressed = false;
	private boolean dragging = false;
	
	public ModuleWindow modsExp, modsMsc, modsCmb, modsPly, modsMvm, modsRen;
	
	public ClickGuiScreen() {
		super(new LiteralText("ClickGui"));
	}
	
	public void initWindows() {
		modsExp = new ModuleWindow(ModuleManager.getModulesInCat(Category.EXPLOITS), "Exploits", len, setLen, 30, 35);
		modsMsc = new ModuleWindow(ModuleManager.getModulesInCat(Category.MISC), "Misc", len, setLen, 100, 35);
		modsCmb = new ModuleWindow(ModuleManager.getModulesInCat(Category.COMBAT), "Combat", len, setLen, 170, 35);
		modsPly = new ModuleWindow(ModuleManager.getModulesInCat(Category.PLAYER), "Player", len, setLen, 240, 35);
		modsMvm = new ModuleWindow(ModuleManager.getModulesInCat(Category.MOVEMENT), "Movement", len, setLen, 310, 35);
		modsRen = new ModuleWindow(ModuleManager.getModulesInCat(Category.RENDER), "Render", len, setLen, 380, 35);
	}
	
	public void onClose() {
		ModuleManager.getModuleByName("ClickGui").setToggled(false);
		this.minecraft.openScreen(null);
	}
	
	public void render(int int_1, int int_2, float float_1) {
		this.renderBackground();
		font.draw("BleachHack-1.14-" + BleachHack.VERSION, 3, 3, 0x305090);
		font.draw("BleachHack-1.14-" + BleachHack.VERSION, 2, 2, 0x6090d0);
		font.drawWithShadow("Binds are changed in the control settings" , 2, height-10, 0xff9999);
		font.drawWithShadow("Use .guireset to reset the gui" , 2, height-20, 0x9999ff);
		modsExp.draw(int_1, int_2, lMousePressed, rMousePressed, len, setLen, dragging);
		modsMsc.draw(int_1, int_2, lMousePressed, rMousePressed, len, setLen, dragging);
		modsCmb.draw(int_1, int_2, lMousePressed, rMousePressed, len, setLen, dragging);
		modsPly.draw(int_1, int_2, lMousePressed, rMousePressed, len, setLen, dragging);
		modsMvm.draw(int_1, int_2, lMousePressed, rMousePressed, len, setLen, dragging);
		modsRen.draw(int_1, int_2, lMousePressed, rMousePressed, len, setLen, dragging);
		
		lMousePressed = false;
		rMousePressed = false;
		dragging = false;
		len = (int) Math.round(ModuleManager.getModuleByName("ClickGui")
				.getSettings().get(0).toSlider().getValue());
		setLen = (int) Math.round(ModuleManager.getModuleByName("ClickGui")
				.getSettings().get(1).toSlider().getValue());
		super.render(int_1, int_2, float_1);
	}
	
	public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
		if(p_mouseClicked_5_ == 0) lMousePressed = true;
		else if(p_mouseClicked_5_ == 1) rMousePressed = true;
		
		return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
	}
	
	public boolean mouseDragged(double p_mouseDragged_1_, double p_mouseDragged_3_, int p_mouseDragged_5_, double p_mouseDragged_6_, double p_mouseDragged_8_) {
		if(p_mouseDragged_5_ == 0) dragging = true;
		return super.mouseDragged(p_mouseDragged_1_, p_mouseDragged_3_, p_mouseDragged_5_, p_mouseDragged_6_, p_mouseDragged_8_);
	}
	
	public void resetGui() {
		modsExp.setPos(30, 35);
		modsMsc.setPos(100, 35);
		modsCmb.setPos(170, 35);
		modsPly.setPos(240, 35);
		modsMvm.setPos(310, 35);
		modsRen.setPos(380, 35);
	}
}
