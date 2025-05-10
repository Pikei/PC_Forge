package com.pc_forge.backend.model.entity.product.gpu;

import com.pc_forge.backend.model.entity.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Klasa będąca reprezentacją encji graphics_card z bazy danych. Dziedziczy ona z klasy {@link Product},
 * dodając swoje własne pola.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "graphics_card", schema = "public")
@PrimaryKeyJoinColumn(name = "ean")
@DiscriminatorValue("GPU")
public final class GraphicsCard extends Product {
    /**
     * Producent chipsetu układu graficznego
     */
    @Size(max = 100)
    @NotNull
    @Column(name = "chipset_producer", nullable = false, length = 100)
    private String chipsetProducer;

    /**
     * Chipset karty graficznej
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "chipset", nullable = false, length = 200)
    private String chipset;

    /**
     * Częstotliwość taktowania rdzenia karty graficznej
     */
    @NotNull
    @Column(name = "core_frequency", nullable = false)
    private Integer coreFrequency;

    /**
     * Maksymalna częstotliwość taktowania rdzenia karty graficznej
     */
    @NotNull
    @Column(name = "max_core_frequency", nullable = false)
    private Integer maxCoreFrequency;

    /**
     * Liczba procesorów strumieniujących
     */
    @NotNull
    @Column(name = "stream_processors", nullable = false)
    private Integer streamProcessors;

    /**
     * Liczba jednostek ROP
     */
    @NotNull
    @Column(name = "rop_units", nullable = false)
    private Integer ropUnits;

    /**
     * Liczba jednostek teksturujących
     */
    @NotNull
    @Column(name = "texturing_units", nullable = false)
    private Integer texturingUnits;

    /**
     * Liczba rdzeni RT
     */
    @NotNull
    @Column(name = "rt_cores", nullable = false)
    private Integer rtCores;

    /**
     * Liczba rdzeni Tensor
     */
    @NotNull
    @Column(name = "tensor_cores", nullable = false)
    private Integer tensorCores;

    /**
     * Zastosowana technologia DLSS
     */
    @Size(max = 100)
    @NotNull
    @Column(name = "dlss", nullable = false, length = 100)
    private String dlss;

    /**
     * Złącze karty graficznej
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "connector", nullable = false, length = 200)
    private String connector;

    /**
     * Długość karty graficznej
     */
    @NotNull
    @Column(name = "card_length", nullable = false)
    private Integer cardLength;

    /**
     * Rozdzielczość generowanego obrazu
     */
    @Size(max = 100)
    @NotNull
    @Column(name = "resolution", nullable = false, length = 100)
    private String resolution;

    /**
     * Rekomendowana moc zasilacza
     */
    @NotNull
    @Column(name = "recommended_ps_power", nullable = false)
    private Integer recommendedPsPower;

    /**
     * Flaga określająca, czy karta graficzna posiada podświetlenie, czy nie
     */
    @NotNull
    @Column(name = "lightning", nullable = false)
    private Boolean lightning = false;

    /**
     * Ilość pamięci VRAM karty graficznej w GB
     */
    @NotNull
    @Column(name = "ram", nullable = false)
    private Integer ram;

    /**
     * Typ pamięci VRAM karty graficznej
     */
    @Size(max = 100)
    @NotNull
    @Column(name = "ram_type", nullable = false, length = 100)
    private String ramType;

    /**
     * Liczba bitów szyny danych karty graficznej
     */
    @NotNull
    @Column(name = "data_bus", nullable = false)
    private Integer dataBus;

    /**
     * Częstotliwość pamięci VRAM karty graficznej
     */
    @NotNull
    @Column(name = "memory_frequency", nullable = false)
    private Integer memoryFrequency;

    /**
     * Rodzaj układu chłodzenia karty graficznej
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "cooling_type", nullable = false, length = 200)
    private String coolingType;

    /**
     * Liczba wiatraków
     */
    @NotNull
    @Column(name = "number_of_fans", nullable = false)
    private Integer numberOfFans;

}