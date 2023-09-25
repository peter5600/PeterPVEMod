package net.peter.peterpvemod.contract;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;

import java.io.*;
import java.util.ArrayList; // import the ArrayList class
public class PlayerContract {
    private Contract contract;

    public Contract getContracts(){
        return contract;
    }

    private int getRandomNumber(){
        return RandomSource.createNewThreadLocalInstance().nextInt(10) + 1;
    }

    public void addContract(){
        Contract c = new Contract();
        c.setContractName("Test Contract Name #1");
        c.setItemRequiredName("Gun Powders");
        c.setItemsRequired(getRandomNumber());
        c.setMobToKill("Creepers");
        c.setContractString(String.format("%s: Kill %d %s and hand in %d %s",c.getContractName(), c.getItemsRequired(), c.getMobToKill(), c.getItemsRequired(), c.getItemRequiredName()));
        this.contract = c;
    }

    public void copyFrom(PlayerContract source){
        this.contract = source.contract;
    }

    public void convertContractToBytesAndSaveNBTData(CompoundTag nbt)  {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(this.contract);
            out.flush();
            byte[] yourBytes = bos.toByteArray();
            nbt.putByteArray("contract", yourBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }
    }

    public void loadNBTBytesIntoContract(CompoundTag nbt){
        ByteArrayInputStream bis = new ByteArrayInputStream(nbt.getByteArray("contract"));
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            Object o = in.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }
    }
}
