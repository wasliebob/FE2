package forestryextras.items.bees;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.apiculture.EnumBeeType;
import forestry.api.core.ItemInterface;
import forestry.api.genetics.IAllele;
import forestryextras.main.init.Bees;

public class GenomeManager {

	private static IAllele[] getTemplateModBase()
	{
		IAllele[] genome = new IAllele[EnumBeeChromosome.values().length];

		genome[EnumBeeChromosome.SPECIES.ordinal()] = Bees.draconicBee;
		genome[EnumBeeChromosome.SPEED.ordinal()] =  Allele.getBaseAllele("speedSlowest");
		genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanShorter");
		genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityNormal");
		genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceNone");
		genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolFalse");
		genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceNone");
		genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolFalse");
		genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolFalse");
		genome[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.getBaseAllele("flowersVanilla");
		genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringSlowest");
		genome[EnumBeeChromosome.TERRITORY.ordinal()] = Allele.getBaseAllele("territoryDefault");
		genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectNone");

		return genome;
	}
	
		public static IAllele[] getDraconicTemplate()
		{
			IAllele[] genome = getTemplateModBase();
			
			genome[EnumBeeChromosome.SPECIES.ordinal()] = Bees.draconicBee;
			genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
			genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
			//genome[EnumBeeChromosome.SPEED.ordinal()] =  Allele.getBaseAllele("speedNormal");

			return genome;
		}
		
		public static IAllele[] getReinforcedTemplate()
		{
			IAllele[] genome = getTemplateModBase();
			
			genome[EnumBeeChromosome.SPECIES.ordinal()] = Bees.reinforcedBee;
			genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
			genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
			//genome[EnumBeeChromosome.SPEED.ordinal()] =  Allele.getBaseAllele("speedNormal");

			return genome;
		}
		
		public static IAllele[] getThaumiumTemplate()
		{
			IAllele[] genome = getTemplateModBase();
			
			genome[EnumBeeChromosome.SPECIES.ordinal()] = Bees.thaumiumBee;
			genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
			genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
			//genome[EnumBeeChromosome.SPEED.ordinal()] =  Allele.getBaseAllele("speedNormal");

			return genome;
		}
		
		public static IAllele[] getDarkThaumiumTemplate()
		{
			IAllele[] genome = getTemplateModBase();
			
			genome[EnumBeeChromosome.SPECIES.ordinal()] = Bees.darkThaumiumBee;
			genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
			genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
			//genome[EnumBeeChromosome.SPEED.ordinal()] =  Allele.getBaseAllele("speedFastest");

			return genome;
		}
		
		public static IAllele[] getPokefenniumTemplate()
		{
			IAllele[] genome = getTemplateModBase();
			
			genome[EnumBeeChromosome.SPECIES.ordinal()] = Bees.pokefenniumBee;
			genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
			genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
			//genome[EnumBeeChromosome.SPEED.ordinal()] =  Allele.getBaseAllele("speedFastest");

			return genome;
		}
		
		public static IAllele[] getFairyTemplate()
		{
			IAllele[] genome = getTemplateModBase();
			
			genome[EnumBeeChromosome.SPECIES.ordinal()] = Bees.fairyBee;
			genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
			genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
			//genome[EnumBeeChromosome.SPEED.ordinal()] =  Allele.getBaseAllele("speedFastest");

			return genome;
		}
		
		public static IAllele[] getElectricalTemplate()
		{
			IAllele[] genome = getTemplateModBase();
			
			genome[EnumBeeChromosome.SPECIES.ordinal()] = Bees.electricalBee;
			genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
			genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
			//genome[EnumBeeChromosome.SPEED.ordinal()] =  Allele.getBaseAllele("speedFastest");

			return genome;
		}
		
		public static IAllele[] getConductiveTemplate()
		{
			IAllele[] genome = getTemplateModBase();
			
			genome[EnumBeeChromosome.SPECIES.ordinal()] = Bees.conductiveBee;
			genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
			genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
			//genome[EnumBeeChromosome.SPEED.ordinal()] =  Allele.getBaseAllele("speedFastest");

			return genome;
		}
		
		public static IAllele[] getEnergeticTemplate()
		{
			IAllele[] genome = getTemplateModBase();
			
			genome[EnumBeeChromosome.SPECIES.ordinal()] = Bees.energeticBee;
			genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
			genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
			//genome[EnumBeeChromosome.SPEED.ordinal()] =  Allele.getBaseAllele("speedFastest");

			return genome;
		}
		
		public static IAllele[] getVibrantTemplate()
		{
			IAllele[] genome = getTemplateModBase();
			
			genome[EnumBeeChromosome.SPECIES.ordinal()] = Bees.vibrantBee;
			genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
			genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
			//genome[EnumBeeChromosome.SPEED.ordinal()] =  Allele.getBaseAllele("speedFastest");

			return genome;
		}
		
		public static IAllele[] getPulsatingTemplate()
		{
			IAllele[] genome = getTemplateModBase();
			
			genome[EnumBeeChromosome.SPECIES.ordinal()] = Bees.pulsatingBee;
			genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
			genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
			//genome[EnumBeeChromosome.SPEED.ordinal()] =  Allele.getBaseAllele("speedFastest");

			return genome;
		}
		
		public static IAllele[] getSilverwoodTemplate()
		{
			IAllele[] genome = getTemplateModBase();
			
			genome[EnumBeeChromosome.SPECIES.ordinal()] = Bees.silverwoodBee;
			genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
			genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
			//genome[EnumBeeChromosome.SPEED.ordinal()] =  Allele.getBaseAllele("speedFastest");

			return genome;
		}
		
		public static IAllele[] getGreatwoodTemplate()
		{
			IAllele[] genome = getTemplateModBase();
			
			genome[EnumBeeChromosome.SPECIES.ordinal()] = Bees.greatwoodBee;
			genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
			genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
			//genome[EnumBeeChromosome.SPEED.ordinal()] =  Allele.getBaseAllele("speedFastest");

			return genome;
		}
		
		public static IAllele[] getBotanistTemplate()
		{
			IAllele[] genome = getTemplateModBase();
			
			genome[EnumBeeChromosome.SPECIES.ordinal()] = Bees.botanistBee;
			genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolTrue");
			genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolTrue");
			//genome[EnumBeeChromosome.SPEED.ordinal()] =  Allele.getBaseAllele("speedFastest");

			return genome;
		}
		
		public static ItemStack getBeeNBTForSpecies(Species species, EnumBeeType beeType)
		{
			ItemStack taggedBee;
			switch (beeType)
			{
				case PRINCESS:
					taggedBee = ItemInterface.getItem("beePrincessGE");
					break;
				case QUEEN:
					taggedBee = ItemInterface.getItem("beeQueenGE");
					break;
				case DRONE:
				default:
					taggedBee = ItemInterface.getItem("beeDroneGE");
					break;
			}
			
			NBTTagCompound tags = new NBTTagCompound();
			
			addGeneToCompound(EnumBeeChromosome.SPECIES, species, tags);
			
			taggedBee.setTagCompound(tags);
			
			return taggedBee;
		}
		
		private static void addGeneToCompound(EnumBeeChromosome gene, IAllele allele, NBTTagCompound compound)
		{
			NBTTagCompound geneRoot = new NBTTagCompound();
			compound.setTag("Genome", geneRoot);
			NBTTagList chromosomes = new NBTTagList();
			geneRoot.setTag("Chromosomes", chromosomes);
			
			NBTTagCompound selectedGene = new NBTTagCompound();
			chromosomes.appendTag(selectedGene);
			
			selectedGene.setByte("Slot", (byte)gene.ordinal());
			selectedGene.setString("UID0", allele.getUID());
			selectedGene.setString("UID1", allele.getUID());		
		}
}