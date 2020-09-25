package amadeuszx.petclinic.springpetclinic.services.map;

import amadeuszx.petclinic.springpetclinic.model.Vet;
import amadeuszx.petclinic.springpetclinic.services.SpecialtiesService;
import amadeuszx.petclinic.springpetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
private final SpecialtiesService specialtiesService;

    public VetServiceMap(final SpecialtiesService specialtiesService) {this.specialtiesService = specialtiesService;}

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(final Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(final Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(final Vet object) {
        if(object!=null){
            if(!object.getSpecialities().isEmpty()){
                object.getSpecialities().forEach(speciality -> {
                    if (speciality.getId()==null){
                   speciality.setId(specialtiesService.save(speciality).getId());
                    }
                });
            }
        }

        return super.save(object);
    }

    @Override
    public Vet findById(final Long id) {
        return super.findById(id);
    }
}
