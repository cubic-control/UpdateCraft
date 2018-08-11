package com.cubic_control.UpdateCraft.Entities;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.cubic_control.UpdateCraft.Items.MItems;
import com.cubic_control.UpdateCraft.Main.MainRegistry;
import com.cubic_control.UpdateCraft.Network.ArmorStandInteractMessage;
import com.cubic_control.UpdateCraft.Network.ModPacketHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityArmorStand extends EntityLiving {
	private static final Rotations DEFAULT_HEAD_ROTATION = new Rotations(0.0F, 0.0F, 0.0F);
	private static final Rotations DEFAULT_BODY_ROTATION = new Rotations(0.0F, 0.0F, 0.0F);
	private static final Rotations DEFAULT_LEFTARM_ROTATION = new Rotations(-10.0F, 0.0F, -10.0F);
	private static final Rotations DEFAULT_RIGHTARM_ROTATION = new Rotations(-15.0F, 0.0F, 10.0F);
	private static final Rotations DEFAULT_LEFTLEG_ROTATION = new Rotations(-1.0F, 0.0F, -1.0F);
	private static final Rotations DEFAULT_RIGHTLEG_ROTATION = new Rotations(1.0F, 0.0F, 1.0F);
	private boolean canInteract;
	private long punchCooldown;
	private Rotations headRotation;
	private Rotations bodyRotation;
	private Rotations leftArmRotation;
	private Rotations rightArmRotation;
	private Rotations leftLegRotation;
	private Rotations rightLegRotation;
	
	public EntityArmorStand(World world) {
		super(world);
		headRotation = DEFAULT_HEAD_ROTATION;
		bodyRotation = DEFAULT_BODY_ROTATION;
		leftArmRotation = DEFAULT_LEFTARM_ROTATION;
		rightArmRotation = DEFAULT_RIGHTARM_ROTATION;
		leftLegRotation = DEFAULT_LEFTLEG_ROTATION;
		rightLegRotation = DEFAULT_RIGHTLEG_ROTATION;
		noClip = hasNoGravity();
		setSize(0.5F, 1.975F);
	}

	public EntityArmorStand(World world, double x, double y, double z) {
		this(world);
		setPosition(x, y, z);
	}
	@Override
	protected void entityInit() {
		super.entityInit();
		addRotationsToDataWatcher(12, DEFAULT_HEAD_ROTATION);
		addRotationsToDataWatcher(15, DEFAULT_BODY_ROTATION);
		addRotationsToDataWatcher(18, DEFAULT_LEFTARM_ROTATION);
		addRotationsToDataWatcher(21, DEFAULT_RIGHTARM_ROTATION);
		addRotationsToDataWatcher(24, DEFAULT_LEFTLEG_ROTATION);
		addRotationsToDataWatcher(27, DEFAULT_RIGHTLEG_ROTATION);
		dataWatcher.addObject(30, (byte) 0);
		func_110163_bv();
	}
	
	private void addRotationsToDataWatcher(int index, Rotations rotations) {
		dataWatcher.addObject(index, rotations.getX());
		dataWatcher.addObject(index + 1, rotations.getY());
		dataWatcher.addObject(index + 2, rotations.getZ());
	}
	@Override
	protected void updateEntityActionState() {}
	@Override
	public ItemStack getPickedResult(MovingObjectPosition mop) {
		return new ItemStack(MItems.armor_stand);
	}
	
	@SideOnly(Side.CLIENT)
	public ItemStack getCurrentArmor(int slotIn) {
		return getEquipmentInSlot(slotIn + 1);
	}
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean("Invisible", isInvisible());
		compound.setBoolean("Small", isSmall());
		compound.setBoolean("ShowArms", getShowArms());
		compound.setBoolean("NoGravity", hasNoGravity());
		compound.setBoolean("NoBasePlate", hasNoBasePlate());
		compound.setTag("Pose", readPoseFromNBT());
	}
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		setInvisible(compound.getBoolean("Invisible"));
		setSmall(compound.getBoolean("Small"));
		setShowArms(compound.getBoolean("ShowArms"));
		setNoGravity(compound.getBoolean("NoGravity"));
		setNoBasePlate(compound.getBoolean("NoBasePlate"));
		noClip = hasNoGravity();
		NBTTagCompound nbttagcompound1 = compound.getCompoundTag("Pose");
		writePoseToNBT(nbttagcompound1);
	}

	private void writePoseToNBT(NBTTagCompound compound) {
		NBTTagList nbttaglist = compound.getTagList("Head", 5);

		if(nbttaglist.tagCount() > 0){
			setHeadRotation(new Rotations(nbttaglist));
		}else{
			setHeadRotation(DEFAULT_HEAD_ROTATION);
		}
		NBTTagList nbttaglist1 = compound.getTagList("Body", 5);

		if(nbttaglist1.tagCount() > 0){
			setBodyRotation(new Rotations(nbttaglist1));
		}else{
			setBodyRotation(DEFAULT_BODY_ROTATION);
		}
		NBTTagList nbttaglist2 = compound.getTagList("LeftArm", 5);

		if(nbttaglist2.tagCount() > 0){
			setLeftArmRotation(new Rotations(nbttaglist2));
		}else{
			setLeftArmRotation(DEFAULT_LEFTARM_ROTATION);
		}
		NBTTagList nbttaglist3 = compound.getTagList("RightArm", 5);
		
		if(nbttaglist3.tagCount() > 0){
			setRightArmRotation(new Rotations(nbttaglist3));
		}else{
			setRightArmRotation(DEFAULT_RIGHTARM_ROTATION);
		}
		NBTTagList nbttaglist4 = compound.getTagList("LeftLeg", 5);
		
		if(nbttaglist4.tagCount() > 0){
			setLeftLegRotation(new Rotations(nbttaglist4));
		}else{
			setLeftLegRotation(DEFAULT_LEFTLEG_ROTATION);
		}
		NBTTagList nbttaglist5 = compound.getTagList("RightLeg", 5);
		
		if(nbttaglist5.tagCount() > 0){
			setRightLegRotation(new Rotations(nbttaglist5));
		}else{
			setRightLegRotation(DEFAULT_RIGHTLEG_ROTATION);
		}
	}
	
	private NBTTagCompound readPoseFromNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		
		if(!DEFAULT_HEAD_ROTATION.equals(headRotation)){
			nbt.setTag("Head", headRotation.writeToNBT());
		}
		if(!DEFAULT_BODY_ROTATION.equals(bodyRotation)){
			nbt.setTag("Body", bodyRotation.writeToNBT());
		}
		if(!DEFAULT_LEFTARM_ROTATION.equals(leftArmRotation)){
			nbt.setTag("LeftArm", leftArmRotation.writeToNBT());
		}
		if(!DEFAULT_RIGHTARM_ROTATION.equals(rightArmRotation)){
			nbt.setTag("RightArm", rightArmRotation.writeToNBT());
		}
		if(!DEFAULT_LEFTLEG_ROTATION.equals(leftLegRotation)){
			nbt.setTag("LeftLeg", leftLegRotation.writeToNBT());
		}
		if(!DEFAULT_RIGHTLEG_ROTATION.equals(rightLegRotation)){
			nbt.setTag("RightLeg", rightLegRotation.writeToNBT());
		}
		return nbt;
	}
	@Override
	public boolean canBePushed() {
		return false;
	}
	@Override
	protected void collideWithEntity(Entity entity) {}
	@Override
	protected void collideWithNearbyEntities() {
		List<Entity>list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(0.2, 0, 0.2));
		
		if(list != null && !list.isEmpty()){
			for(int i = 0; i < list.size(); i++){
				Entity entity = list.get(i);
				
				if(entity instanceof EntityMinecart && ((EntityMinecart) entity).getMinecartType() == 0){
					entity.applyEntityCollision(this);
				}
			}
		}
	}
	@Override
	public boolean interact(EntityPlayer player) {
		if(worldObj.isRemote){
			ModPacketHandler.snw.sendToServer(new ArmorStandInteractMessage(worldObj.provider.dimensionId, this, player));
			return true;
		}
		return false;
	}
	
	public boolean interact(EntityPlayer player, Vec3 hitPos) {
		if(!worldObj.isRemote){
			byte b0 = 0;
			ItemStack itemstack = player.getCurrentEquippedItem();
			boolean flag = itemstack != null;

			if(flag && itemstack.getItem() instanceof ItemArmor){
				ItemArmor itemarmor = (ItemArmor)itemstack.getItem();
				
				if(itemarmor.armorType == 3){
					b0 = 1;
				}else if(itemarmor.armorType == 2){
					b0 = 2;
				}else if(itemarmor.armorType == 1){
					b0 = 3;
				}else if(itemarmor.armorType == 0){
					b0 = 4;
				}
			}
			if(flag && (itemstack.getItem() == Items.skull || itemstack.getItem() == Item.getItemFromBlock(Blocks.pumpkin))){
				b0 = 4;
			}
			byte b1 = 0;
			boolean isSmall = isSmall();
			double d3 = isSmall ? hitPos.yCoord * 2.0D : hitPos.yCoord;

			if(d3 >= 0.1D && d3 < 0.1D + (isSmall ? 0.8D : 0.45D) && getEquipmentInSlot(1) != null){
				b1 = 1;
			}else if(d3 >= 0.9D + (isSmall ? 0.3D : 0.0D) && d3 < 0.9D + (isSmall ? 1.0D : 0.7D) && getEquipmentInSlot(3) != null){
				b1 = 3;
			}else if(d3 >= 0.4D && d3 < 0.4D + (isSmall ? 1.0D : 0.8D) && getEquipmentInSlot(2) != null){
				b1 = 2;
			}else if(d3 >= 1.6D && getEquipmentInSlot(4) != null){
				b1 = 4;
			}
			boolean flag2 = getEquipmentInSlot(b1) != null;

			if(flag && b0 == 0 && !getShowArms()){
				return true;
			}else{
				if(flag){
					func_175422_a(player, b0);
				}else if(flag2){
					func_175422_a(player, b1);
				}
				return true;
			}
		}else{
			return true;
		}
	}
	
	private void func_175422_a(EntityPlayer player, int slot) {
		ItemStack itemstack = getEquipmentInSlot(slot);
		int j = player.inventory.currentItem;
		ItemStack itemstack1 = player.inventory.getStackInSlot(j);
		ItemStack itemstack2;
		
		if(player.capabilities.isCreativeMode && (itemstack == null || itemstack.getItem() == Item.getItemFromBlock(Blocks.air)) && itemstack1 != null){
			itemstack2 = itemstack1.copy();
			itemstack2.stackSize = 1;
			setCurrentItemOrArmor(slot, itemstack2);
		}else if(itemstack1 != null && itemstack1.stackSize > 1){
			if(itemstack == null){
				itemstack2 = itemstack1.copy();
				itemstack2.stackSize = 1;
				setCurrentItemOrArmor(slot, itemstack2);
				itemstack1.stackSize--;
			}
		}else{
			setCurrentItemOrArmor(slot, itemstack1);
			player.inventory.setInventorySlotContents(j, itemstack);
		}
	}
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(!worldObj.isRemote && !canInteract){
			if(DamageSource.outOfWorld.equals(source)){
				setDead();
				return false;
			}else if(isEntityInvulnerable()){
				return false;
			}else if(source.isExplosion()){
				dropequipment();
				setDead();
				return false;
			}else if(DamageSource.inFire.equals(source)){
				if(!isBurning()){
					setFire(5);
				}else{
					damageArmorStand(0.15F);
				}
				return false;
			}else if(DamageSource.onFire.equals(source) && getHealth() > 0.5F){
				damageArmorStand(4.0F);
				return false;
			}else{
				boolean flag = "arrow".equals(source.getDamageType());
				boolean flag1 = "player".equals(source.getDamageType());

				if(!flag1 && !flag){
					return false;
				}else{
					if(source.getSourceOfDamage() instanceof EntityArrow){
						source.getSourceOfDamage().setDead();
					}
					if(source.getEntity() instanceof EntityPlayer && !((EntityPlayer) source.getEntity()).capabilities.allowEdit){
						return false;
					}else if(source.getEntity() instanceof EntityPlayer && ((EntityPlayer) source.getEntity()).capabilities.isCreativeMode){
						playParticles();
						setDead();
						return false;
					}else{
						long i = worldObj.getTotalWorldTime();
						
						if(i - punchCooldown > 5L && !flag){
							punchCooldown = i;
						}else{
							dropBlock();
							playParticles();
							setDead();
						}
						return false;
					}
				}
			}
		}else{
			return false;
		}
	}
	
	private void playParticles() {
		// if (worldObj instanceof WorldServer)
		// ((WorldServer) worldObj).spawnParticle(EnumParticleTypes.BLOCK_DUST, posX, posY + height / 1.5D, posZ, 10, (double) (width / 4.0F), (double) (height / 4.0F), (double) (width / 4.0F), 0.05D, new int[] { Block.getStateId(Blocks.planks.getDefaultState()) });
	}
	
	private void damageArmorStand(float p_175406_1_) {
		float f1 = getHealth();
		f1 -= p_175406_1_;
		
		if(f1 <= 0.5F){
			dropequipment();
			setDead();
		}else{
			setHealth(f1);
		}
	}
	
	private void dropBlock() {
		entityDropItem(new ItemStack(MItems.armor_stand), 0.0F);
		dropequipment();
	}
	
	private void dropequipment() {
		for(int i = 0; i < 5; i++){
			if(getEquipmentInSlot(i) != null && getEquipmentInSlot(i).stackSize > 0){
				if(getEquipmentInSlot(i) != null){
					entityDropItem(getEquipmentInSlot(i), 0.0F);
					setCurrentItemOrArmor(i, null);
				}
			}
		}
	}
	@Override
	protected float func_110146_f(float p_110146_1_, float p_110146_2_) {
		prevRenderYawOffset = prevRotationYaw;
		renderYawOffset = rotationYaw;
		return 0.0F;
	}
	@Override
	public float getEyeHeight() {
		return isChild() ? height * 0.5F : height * 0.9F;
	}
	@Override
	public void moveEntityWithHeading(float strafe, float forward) {
		if(!hasNoGravity()){
			super.moveEntityWithHeading(strafe, forward);
		}
	}
	@Override
	public void onUpdate() {
		super.onUpdate();
		Rotations rotations = getRotations(12);
		
		if(!headRotation.equals(rotations)){
			setHeadRotation(rotations);
		}
		Rotations rotations1 = getRotations(15);
		
		if(!bodyRotation.equals(rotations1)){
			setBodyRotation(rotations1);
		}
		Rotations rotations2 = getRotations(18);
		
		if(!leftArmRotation.equals(rotations2)){
			setLeftArmRotation(rotations2);
		}
		Rotations rotations3 = getRotations(21);
		
		if(!rightArmRotation.equals(rotations3)){
			setRightArmRotation(rotations3);
		}
		Rotations rotations4 = getRotations(24);
		
		if(!leftLegRotation.equals(rotations4)){
			setLeftLegRotation(rotations4);
		}
		Rotations rotations5 = getRotations(27);
		
		if(!rightLegRotation.equals(rotations5)){
			setRightLegRotation(rotations5);
		}
	}
	
	private Rotations getRotations(int index) {
		return new Rotations(dataWatcher.getWatchableObjectFloat(index), dataWatcher.getWatchableObjectFloat(index + 1), dataWatcher.getWatchableObjectFloat(index + 2));
	}
	@Override
	public void setInvisible(boolean invisible) {
		canInteract = invisible;
		super.setInvisible(invisible);
	}
	@Override
	public boolean isChild() {
		return isSmall();
	}
	
	private void setSmall(boolean small) {
		byte b0 = dataWatcher.getWatchableObjectByte(30);
		
		if(small){
			b0 = (byte) (b0 | 1);
		}else{
			b0 &= -2;
		}
		dataWatcher.updateObject(30, b0);
	}
	
	public boolean isSmall() {
		return (dataWatcher.getWatchableObjectByte(30) & 1) != 0;
	}
	
	private void setNoGravity(boolean noGravity) {
		byte b0 = dataWatcher.getWatchableObjectByte(30);
		
		if(noGravity){
			b0 = (byte) (b0 | 2);
		}else{
			b0 &= -3;
		}
		dataWatcher.updateObject(30, b0);
	}
	
	public boolean hasNoGravity() {
		return (dataWatcher.getWatchableObjectByte(30) & 2) != 0;
	}
	
	private void setShowArms(boolean showArms) {
		byte b0 = dataWatcher.getWatchableObjectByte(30);

		if(showArms){
			b0 = (byte) (b0 | 4);
		}else{
			b0 &= -5;
		}
		dataWatcher.updateObject(30, b0);
	}
	
	public boolean getShowArms() {
		return (dataWatcher.getWatchableObjectByte(30) & 4) != 0;
	}
	
	private void setNoBasePlate(boolean noBasePlate) {
		byte b0 = dataWatcher.getWatchableObjectByte(30);
		
		if(noBasePlate){
			b0 = (byte) (b0 | 8);
		}else{
			b0 &= -9;
		}
		dataWatcher.updateObject(30, b0);
	}
	
	public boolean hasNoBasePlate() {
		return (dataWatcher.getWatchableObjectByte(30) & 8) != 0;
	}
	
	public void setHeadRotation(Rotations rotations) {
		headRotation = rotations;
		updateRotations(12, rotations);
	}
	
	public void setBodyRotation(Rotations rotations) {
		bodyRotation = rotations;
		updateRotations(15, rotations);
	}
	
	public void setLeftArmRotation(Rotations rotations) {
		leftArmRotation = rotations;
		updateRotations(18, rotations);
	}
	
	public void setRightArmRotation(Rotations rotations) {
		rightArmRotation = rotations;
		updateRotations(21, rotations);
	}
	
	public void setLeftLegRotation(Rotations rotations) {
		leftLegRotation = rotations;
		updateRotations(24, rotations);
	}
	
	public void setRightLegRotation(Rotations rotations) {
		rightLegRotation = rotations;
		updateRotations(27, rotations);
	}
	
	private void updateRotations(int index, Rotations rotations) {
		dataWatcher.updateObject(index, rotations.getX());
		dataWatcher.updateObject(index + 1, rotations.getY());
		dataWatcher.updateObject(index + 2, rotations.getZ());
	}
	
	public Rotations getHeadRotation() {
		return headRotation;
	}
	
	public Rotations getBodyRotation() {
		return bodyRotation;
	}
	
	@SideOnly(Side.CLIENT)
	public Rotations getLeftArmRotation() {
		return leftArmRotation;
	}
	
	@SideOnly(Side.CLIENT)
	public Rotations getRightArmRotation() {
		return rightArmRotation;
	}
	
	@SideOnly(Side.CLIENT)
	public Rotations getLeftLegRotation() {
		return leftLegRotation;
	}
	
	@SideOnly(Side.CLIENT)
	public Rotations getRightLegRotation() {
		return rightLegRotation;
	}
	@Override
	protected void updatePotionEffects() {}
}
