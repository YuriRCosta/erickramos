'use client'

import React, { useState } from 'react';
import Select from "react-select";

const ModalValvula = ({ closeModal, handleSave }) => {
    const [formData, setFormData] = useState({
        nome: '',
        preco: '',
        qtdEstoque: '',
        tipo: '',
        codigo: '',
    });

    const tipoValvulaOptions = [
        { value: 'ESCAPE', label: 'Escape' },
        { value: 'ADMISSAO', label: 'Admissão' },
    ];

    const handleTipoValvulaChange = (selectedOption) => {
        setFormData({ ...formData, tipo: selectedOption.value });
    };

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const preco = parseFloat(formData.preco);
        const qtdEstoque = parseInt(formData.qtdEstoque);// Converter o campo "preco" para número de ponto flutuante
        handleSave({ ...formData, preco, qtdEstoque });
    };

    return (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
            <div className="bg-black rounded-lg shadow-lg p-6">
                <h2 className="text-2xl font-semibold mb-4">Novo Cadastro</h2>

                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <label htmlFor="nome" className="block mb-1">
                            Cabecote:
                        </label>
                        <input
                            type="text"
                            id="nome"
                            name="nome"
                            value={formData.nome}
                            onChange={handleChange}
                            className="border text-black text-black border-gray-300 rounded px-2 py-1 w-full"
                        />
                    </div>

                    <div className="mb-4">
                        <label htmlFor="nome" className="block mb-1">
                            Preco:
                        </label>
                        <input
                            type="text"
                            id="preco"
                            name="preco"
                            value={formData.preco}
                            onChange={handleChange}
                            className="border text-black text-black border-gray-300 rounded px-2 py-1 w-full"
                        />
                    </div>

                    <div className="mb-4">
                        <label htmlFor="nome" className="block mb-1">
                            Qtd Estoque:
                        </label>
                        <input
                            type="text"
                            id="qtdEstoque"
                            name="qtdEstoque"
                            value={formData.qtdEstoque}
                            onChange={handleChange}
                            className="border text-black text-black border-gray-300 rounded px-2 py-1 w-full"
                        />
                    </div>

                    <div className="mb-4">
                        <label htmlFor="tipoValvula">Tipo de Válvula:</label>
                        <Select
                            className="text-black"
                            placeholder="Selecione o tipo de válvula"
                            id="tipo"
                            options={tipoValvulaOptions}
                            value={tipoValvulaOptions.find(
                                (option) => option.value === formData.tipoValvula
                            )}
                            onChange={handleTipoValvulaChange}
                        />
                    </div>

                    <div className="mb-4">
                        <label htmlFor="nome" className="block mb-1">
                            Codigo:
                        </label>
                        <input
                            type="text"
                            id="codigo"
                            name="codigo"
                            value={formData.codigo}
                            onChange={handleChange}
                            className="border text-black border-gray-300 rounded px-2 py-1 w-full"
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

export default ModalValvula;
