package com.hutech.demo.service;

import com.hutech.demo.model.Type;
import com.hutech.demo.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing types.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class TypeService {

    private final TypeRepository typeRepository;

    /**
     * Retrieve all types from the database.
     * @return a list of types
     */
    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    /**
     * Retrieve a type by its id.
     * @param id the id of the type to retrieve
     * @return an Optional containing the found type or empty if not found
     */
    public Optional<Type> getTypeById(Long id) {
        return typeRepository.findById(id);
    }

    /**
     * Add a new type to the database.
     * @param type the type to add
     */
    public void addType(Type type) {
        typeRepository.save(type);
    }

    /**
     * Update an existing type.
     * @param type the type with updated information
     */
    public void updateType(@NotNull Type type) {
        Type existingType = typeRepository.findById(type.getId())
                .orElseThrow(() -> new IllegalStateException("Type with ID " + type.getId() + " does not exist."));
        // Update type properties here
        existingType.setName(type.getName());
        typeRepository.save(existingType);
    }

    /**
     * Delete a type by its id.
     * @param id the id of the type to delete
     */
    public void deleteTypeById(Long id) {
        if (!typeRepository.existsById(id)) {
            throw new IllegalStateException("Type with ID " + id + " does not exist.");
        }
        typeRepository.deleteById(id);
    }
}
