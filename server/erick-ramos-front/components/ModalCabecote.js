'use client'

import React, { useState } from 'react';
import Select from "react-select";

const ModalCabecote = ({ closeModal, handleSave }) => {
    const [formData, setFormData] = useState({
        nome: '',
        qtdValvulas: '',
        material: '',
    });

    const materialOptions = [
        { value: 'ALUMINIO', label: 'Aluminio' },
        { value: 'FERRO', label: 'Ferro' },
    ];

    const handleMaterialCabecoteChange = (selectedOption) => {
        setFormData({ ...formData, material: selectedOption.value });
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const qtdValvulas = parseInt(formData.qtdValvulas);// Converter o campo "preco" para número de ponto flutuante
        handleSave({ ...formData, qtdValvulas });
    };

    return (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
            <div className="bg-neutral-600 rounded-lg shadow-lg p-6">
                <h2 className="text-2xl font-semibold mb-4">Novo Cadastro</h2>

                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <label htmlFor="nome" className="block mb-1">
                            Nome:
                        </label>
                        <input
                            type="text"
                            id="nome"
                            name="nome"
                            value={formData.nome}
                            onChange={handleChange}
                            className="border text-black border-gray-300 rounded px-2 py-1 w-full"
                        />
                    </div>

                    <div className="mb-4">
                        <label htmlFor="nome" className="block mb-1">
                            Qtd Valvulas:
                        </label>
                        <input
                            type="text"
                            id="qtdValvulas"
                            name="qtdValvulas"
                            value={formData.qtdValvulas}
                            onChange={handleChange}
                            className="border text-black border-gray-300 rounded px-2 py-1 w-full"
                        />
                    </div>

                    <div className="mb-4">
                        <label htmlFor="material">Material:</label>
                        <Select className="text-black" placeholder="Selecione o material"
                            id="material"
                            options={materialOptions}
                            value={materialOptions.find(
                                (option) => option.value === formData.material
                            )}
                            onChange={handleMaterialCabecoteChange}
                        />
                    </div>

                    <div className="flex justify-end">
                        <button
                            type="submit"
                            className="bg-blue-500 text-white rounded px-4 py-2 mr-2"
                        >
                            Salvar
                        </button>
                        <button
                            type="button"
                            onClick={closeModal}
                            className="bg-gray-300 text-gray-700 rounded px-4 py-2"
                        >
                            Cancelar
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default ModalCabecote;
