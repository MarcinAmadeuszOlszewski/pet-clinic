package amadeuszx.petclinic.springpetclinic.services.map;

import amadeuszx.petclinic.springpetclinic.model.Vet;
import amadeuszx.petclinic.springpetclinic.services.SpecialityService;
import amadeuszx.petclinic.springpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile("HashMapVesion")
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
private final SpecialityService specialityService;

    public VetServiceMap(final SpecialityService specialityService) {this.specialityService = specialityService;}

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
                   speciality.setId(specialityService.save(speciality).getId());
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
