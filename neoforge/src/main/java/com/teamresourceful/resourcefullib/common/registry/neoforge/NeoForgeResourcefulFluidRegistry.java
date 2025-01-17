package com.teamresourceful.resourcefullib.common.registry.neoforge;

import com.teamresourceful.resourcefullib.common.fluid.data.FluidData;
import com.teamresourceful.resourcefullib.common.fluid.data.FluidProperties;
import com.teamresourceful.resourcefullib.common.fluid.data.InternalFluidData;
import com.teamresourceful.resourcefullib.common.fluid.neoforge.ResourcefulFluidType;
import com.teamresourceful.resourcefullib.common.fluid.registry.ResourcefulFluidRegistry;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntries;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Collection;

public class NeoForgeResourcefulFluidRegistry implements ResourcefulFluidRegistry {

    private final String id;
    private final DeferredRegister<FluidType> registry;
    private final RegistryEntries<FluidData> entries = new RegistryEntries<>();

    public NeoForgeResourcefulFluidRegistry(String id) {
        this.id = id;
        this.registry = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, id);
    }

    @Override
    public RegistryEntry<FluidData> register(String name, FluidProperties properties) {
        ResourceLocation rid = ResourceLocation.fromNamespaceAndPath(this.id, name);
        return this.entries.add(new Entry(
                rid,
                new InternalFluidData(
                        rid,
                        properties,
                        this.registry.register(name, (id) -> new ResourcefulFluidType(id, properties))
                )
        ));
    }

    @Override
    public Collection<RegistryEntry<FluidData>> getEntries() {
        return this.entries.getEntries();
    }

    @Override
    public void init() {
        this.registry.register(ModLoadingContext.get().getActiveContainer().getEventBus());
    }
}
