package fluxedCrystals.util;

import fluxedCrystals.network.PacketHandler;
import fluxedCrystals.network.message.MessageSyncSeed;
import fluxedCrystals.registry.Seed;
import fluxedCrystals.registry.SeedRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeedEditor extends JFrame
{

	private JPanel contentPane;
	private JTextField fieldLore;
	private JTextField GrowthTime;
	private JTextField Tier;
	private JTextField PowerPerStage;
	private JTextField fieldName;
	private JComboBox boxIngredient;
	private JComboBox weightedDrop;
	private JComboBox weightedDropChance;
	private JColorChooser colorChooser;
	private JComboBox refinerAmount;
	private JComboBox dropMin;
	private JComboBox dropMax;
	private JComboBox refinerOutput;
	private JComboBox ingredientAmount;
	private JComboBox sharp;
	private JComboBox seedReturn;
	private JTextField ingredientMetadata;
	private JTextField weightedDropMetadata;
	private JComboBox type;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public SeedEditor () {
		setType(Type.UTILITY);
		setTitle("Fluxed-Crystals Seed Editor");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 640);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 11, 46, 14);
		contentPane.add(lblName);

		fieldName = new JTextField();
		fieldName.setBounds(269, 8, 515, 20);
		contentPane.add(fieldName);
		fieldName.setColumns(10);

		JLabel lblLore = new JLabel("Lore:");
		lblLore.setBounds(10, 36, 46, 14);
		contentPane.add(lblLore);

		fieldLore = new JTextField();
		fieldLore.setBounds(269, 33, 515, 20);
		contentPane.add(fieldLore);
		fieldLore.setColumns(10);

		JLabel lblIngredient = new JLabel("Ingredient:");
		lblIngredient.setBounds(10, 61, 68, 14);
		contentPane.add(lblIngredient);

		boxIngredient = new JComboBox();
		boxIngredient.setBounds(269, 58, 400, 20);
		for (Object o : Item.itemRegistry.getKeys()) {
			boxIngredient.addItem(o);
		}
		for (Object o : Block.blockRegistry.getKeys()) {
			boxIngredient.addItem(o);
		}
		contentPane.add(boxIngredient);

		JLabel lblWeightedDrop = new JLabel("Weighted Drop:");
		lblWeightedDrop.setBounds(10, 450, 96, 14);
		contentPane.add(lblWeightedDrop);

		weightedDrop = new JComboBox();
		weightedDrop.setBounds(269, 447, 400, 20);
		weightedDrop.addItem("none");
		for (Object o : Item.itemRegistry.getKeys()) {
			weightedDrop.addItem(o);
		}
		for (Object o : Block.blockRegistry.getKeys()) {
			weightedDrop.addItem(o);
		}
		contentPane.add(weightedDrop);

		JLabel lblColor = new JLabel("Color:");
		lblColor.setBounds(10, 145, 46, 14);
		contentPane.add(lblColor);

		colorChooser = new JColorChooser();
		colorChooser.setBounds(269, 114, 515, 147);
		colorChooser.setPreviewPanel(new JComponent()
		{
		});
		colorChooser.setColor(0xFFFFFF);
		contentPane.add(colorChooser);

		JLabel lblDropMin = new JLabel("Drop Min:");
		lblDropMin.setBounds(10, 275, 96, 14);
		contentPane.add(lblDropMin);

		JLabel lblDropMax = new JLabel("Drop Max:");
		lblDropMax.setBounds(10, 300, 96, 14);
		contentPane.add(lblDropMax);

		JLabel lblGrowthTime = new JLabel("Growth Time:");
		lblGrowthTime.setBounds(10, 325, 96, 14);
		contentPane.add(lblGrowthTime);

		GrowthTime = new JTextField();
		GrowthTime.setBounds(269, 322, 515, 20);
		contentPane.add(GrowthTime);
		GrowthTime.setColumns(10);

		JLabel lblTier = new JLabel("Tier:");
		lblTier.setBounds(10, 350, 96, 14);
		contentPane.add(lblTier);

		Tier = new JTextField();
		Tier.setBounds(269, 347, 515, 20);
		contentPane.add(Tier);
		Tier.setColumns(10);

		JLabel lblRefinerOutput = new JLabel("Refiner Output:");
		lblRefinerOutput.setBounds(10, 375, 96, 14);
		contentPane.add(lblRefinerOutput);

		JLabel lblIngredientAmount = new JLabel("Ingredient Amount:");
		lblIngredientAmount.setBounds(10, 84, 249, 14);
		contentPane.add(lblIngredientAmount);

		JLabel lblPowerPerStage = new JLabel("Power Per Stage:");
		lblPowerPerStage.setBounds(10, 425, 215, 14);
		contentPane.add(lblPowerPerStage);

		PowerPerStage = new JTextField();
		PowerPerStage.setBounds(269, 422, 515, 20);
		contentPane.add(PowerPerStage);
		PowerPerStage.setColumns(10);

		JLabel lblWeightedDropChance = new JLabel("Weighted Drop Chance:");
		lblWeightedDropChance.setBounds(10, 475, 249, 14);
		contentPane.add(lblWeightedDropChance);

		weightedDropChance = new JComboBox();
		weightedDropChance.setBounds(269, 472, 515, 20);
		for (int i = 0; i <= 10; i++) {
			weightedDropChance.addItem(i);
		}
		contentPane.add(weightedDropChance);

		JLabel lblRefinerAmount = new JLabel("Refiner Amount:");
		lblRefinerAmount.setBounds(10, 400, 249, 14);
		contentPane.add(lblRefinerAmount);

		refinerAmount = new JComboBox();
		refinerAmount.setBounds(269, 397, 515, 20);
		for (int i = 1; i <= 64; i++) {
			refinerAmount.addItem(i);
		}
		contentPane.add(refinerAmount);

		dropMin = new JComboBox();
		dropMin.setBounds(269, 272, 515, 20);
		for (int i = 1; i <= 64; i++) {
			dropMin.addItem(i);
		}
		contentPane.add(dropMin);

		dropMax = new JComboBox();
		dropMax.setBounds(269, 297, 515, 20);
		for (int i = 1; i <= 64; i++) {
			dropMax.addItem(i);
		}
		contentPane.add(dropMax);

		refinerOutput = new JComboBox();
		refinerOutput.setBounds(268, 372, 516, 20);
		for (int i = 1; i <= 64; i++) {
			refinerOutput.addItem(i);
		}
		contentPane.add(refinerOutput);

		ingredientAmount = new JComboBox();
		ingredientAmount.setBounds(269, 83, 515, 20);
		for (int i = 1; i <= 64; i++) {
			ingredientAmount.addItem(i);
		}
		contentPane.add(ingredientAmount);

		JLabel lblSeedReturn = new JLabel("Seed Return:");
		lblSeedReturn.setBounds(10, 500, 249, 14);
		contentPane.add(lblSeedReturn);

		JLabel lblSharp = new JLabel("Sharp:");
		lblSharp.setBounds(10, 525, 249, 14);
		contentPane.add(lblSharp);

		sharp = new JComboBox();
		sharp.setBounds(269, 522, 515, 20);
		sharp.addItem(true);
		sharp.addItem(false);
		contentPane.add(sharp);

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(10, 581, 89, 23);
		contentPane.add(btnExit);

		seedReturn = new JComboBox();
		seedReturn.setBounds(269, 497, 515, 20);
		for (int i = 0; i <= 64; i++) {
			seedReturn.addItem(i);
		}
		contentPane.add(seedReturn);

		JButton btnSaveNew = new JButton("Save & New");
		btnSaveNew.setBounds(341, 581, 131, 23);
		contentPane.add(btnSaveNew);
		btnSaveNew.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed (ActionEvent e) {
				saveSeed();
				clear();
			}
		});

		JButton btnSaveExit = new JButton("Save & Exit");
		btnSaveExit.setBounds(638, 581, 146, 23);
		contentPane.add(btnSaveExit);

		ingredientMetadata = new JTextField();
		ingredientMetadata.setBounds(679, 58, 105, 20);
		contentPane.add(ingredientMetadata);
		ingredientMetadata.setColumns(10);

		weightedDropMetadata = new JTextField();
		weightedDropMetadata.setBounds(679, 447, 105, 20);
		contentPane.add(weightedDropMetadata);
		weightedDropMetadata.setColumns(10);

		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(10, 550, 46, 14);
		contentPane.add(lblType);

		type = new JComboBox();
		type.setBounds(269, 547, 515, 20);
		contentPane.add(type);
		type.addItem("crop");
		type.addItem("crystal");
		btnSaveExit.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed (ActionEvent e) {
				saveSeed();
				dispose();
			}
		});

		btnExit.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed (ActionEvent e) {
				dispose();
			}
		});

	}

	/**
	 * Launch the application.
	 */
	public static void start () {
		EventQueue.invokeLater(new Runnable()
		{
			public void run () {
				try {
					SeedEditor frame = new SeedEditor();
					frame.setVisible(true);
					frame.setAlwaysOnTop(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void clear () {
		dispose();
		start();
	}

	public void saveSeed () {
		Seed seed = new Seed();
		seed.color = colorChooser.getColor().getRGB();
		seed.dropMax = Integer.parseInt(String.valueOf(dropMax.getSelectedItem()));
		seed.dropMin = Integer.parseInt(String.valueOf(dropMin.getSelectedItem()));
		seed.growthTime = Integer.parseInt(String.valueOf(GrowthTime.getText()));
		ItemStack stack = JsonTools.parseStringIntoItemStack((String) boxIngredient.getSelectedItem());
		if (!ingredientMetadata.getText().isEmpty()) {
			stack.setItemDamage(Integer.parseInt(ingredientMetadata.getText()));
		}
		else {
			stack.setItemDamage(0);
		}
		seed.ingredient = JsonTools.getStringForItemStack(stack, true, false);
		seed.ingredientAmount = Integer.parseInt(String.valueOf(ingredientAmount.getSelectedItem()));
		seed.isSharp = Boolean.parseBoolean(String.valueOf(sharp.getSelectedItem()));
		seed.lore = fieldLore.getText();
		String mod = seed.ingredient.split(":")[0];
		if (!mod.equalsIgnoreCase("minecraft")) {
			seed.modRequired = mod;
		}
		seed.name = fieldName.getText();
		seed.powerPerStage = Integer.parseInt(String.valueOf(PowerPerStage.getText()));
		seed.refinerAmount = Integer.parseInt(String.valueOf(refinerAmount.getSelectedItem()));
		seed.refinerOutput = Integer.parseInt(String.valueOf(refinerOutput.getSelectedItem()));
		seed.seedID = SeedRegistry.getInstance().getNextID();
		seed.seedReturn = Integer.parseInt(String.valueOf(seedReturn.getSelectedItem()));
		seed.tier = Integer.parseInt(String.valueOf(Tier.getText()));
		ItemStack weightedDropstack = JsonTools.parseStringIntoItemStack((String) weightedDrop.getSelectedItem());
		if (!weightedDropMetadata.getText().isEmpty()) {
			weightedDropstack.setItemDamage(Integer.parseInt(weightedDropMetadata.getText()));
		}
		else {
			weightedDropstack.setItemDamage(0);
		}
		if (((String) weightedDrop.getSelectedItem()).equalsIgnoreCase("none")) {
			seed.weightedDrop = JsonTools.getStringForItemStack(weightedDropstack, true, false);
			seed.weigthedDropChance = Integer.parseInt(String.valueOf(weightedDropChance.getSelectedItem()));
		}
		seed.type = (String) type.getSelectedItem();
		SeedRegistry.getInstance().addTemplateSeed(seed);
		SeedRegistry.getInstance().Save();
		PacketHandler.INSTANCE.sendToAll(new MessageSyncSeed(seed));
	}
}
