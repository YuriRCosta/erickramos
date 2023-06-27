'use client'

import React, {useEffect, useState} from "react";
import ModalSelo from "../../components/ModalSelo";
import Api from "../../services/api";
import Navbar from "../../components/Navbar";
import api from "../../services/api";

export default function Selo() {
    const [selos, setSelos] = useState([]);

    const [medidaPesquisa, setMedidaPesquisa] = useState('');
    const [nomePesquisa, setNomePesquisa] = useState('');

    const accessToken = localStorage.getItem('accessToken');

    const [isModalOpen, setIsModalOpen] = useState(false);
    const [editFormData, setEditFormData] = useState({
        id: '',
        nome: '',
        preco: '',
        medida: '',
        qtdEstoque: '',
    });

    async function carregarSelos() {
        const response = await Api.get('selo', {
            headers: {
                Authorization: `Bearer ${accessToken}`
            },
        })
        setSelos(response.data);
    }

    const openModal = (id) => {
        setEditFormData(selos.find(selo => selo.id === id));
        setIsModalOpen(true);
    };

    const closeModal = () => {
        setIsModalOpen(false);
    };

    const handleMedidaChange = (e) => {
        setMedidaPesquisa(e.target.value);
    };

    const handleNomeChange = (e) => {
        setNomePesquisa(e.target.value);
    };

    const handleSave = async (formData) => {
        try {
            const response = await Api.post('/selo', formData, {
                headers: {
                    Authorization: `Bearer ${accessToken}`
                },
            });
            carregarSelos();
            closeModal();
        } catch (error) {
            console.error(error);
        }
    };

    const handlePesquisar = () => {
        if (medidaPesquisa) {
            Api
                .get(`/selo/medida/${medidaPesquisa}`, {
                    headers: {
                        Authorization: `Bearer ${accessToken}`
                    }
                })
                .then((response) => {
                    const resultado = response.data;
                    console.log(resultado);
                    setSelos(resultado);
                })
                .catch((error) => {
                    console.error(error);
                });
        } else if (nomePesquisa) {
            Api
                .get(`/selo/nome/${nomePesquisa}`, {
                    headers: {
                        Authorization: `Bearer ${accessToken}`
                    }
                })
                .then((response) => {
                    const resultado = response.data;
                    console.log(resultado);
                    setSelos(resultado);
                })
                .catch((error) => {
                    console.error(error);
                });
        } else {
            console.log('Informe um critério de pesquisa válido.');
        }
    };

    async function deleteSelo(id) {
        const confirmDelete = window.confirm('Tem certeza que deseja deletar?');

        if (!confirmDelete) {
            return;
        }
        try {
            await Api.delete(`selo/${id}`, {
                headers: {
                    Authorization: `Bearer ${accessToken}`
                }
            });
            setSelos(selos.filter(selo => selo.id !== id));
        } catch (e) {
            alert('Falha ao deletar selo, tente novamente.');
        }
    }

    useEffect(() => {
        carregarSelos();
    }, []);

    return (
        <div>
            <Navbar />
            <div className="flex items-center bg-white justify-center h-screen">
                <div className="max-w-fit w-full p-6 bg-neutral-500 rounded-lg shadow-md text-center">
                    <h1 className="text-2xl font-semibold mb-4">Selos</h1>

                    <div className="flex flex-col items-center">
                        <input
                            type="text"
                            value={medidaPesquisa}
                            onChange={handleMedidaChange}
                            placeholder="Pesquisar por medida"
                            className="px-4 mb-2 text-black py-2 border border-gray-300 rounded-lg focus:outline-none focus:border-blue-500"
                        />
                        <input
                            type="text"
                            value={nomePesquisa}
                            onChange={handleNomeChange}
                            placeholder="Pesquisar por nome"
                            className="px-4 mb-2 py-2 text-black border border-gray-300 rounded-lg focus:outline-none focus:border-blue-500"
                        />
                        <div className="flex flex-row">
                            <button
                                onClick={handlePesquisar}
                                className="px-4 mr-2 py-2 text-white bg-blue-500 rounded-lg hover:bg-blue-600 focus:outline-none"
                            >
                                Pesquisar
                            </button>
                            <button
                                onClick={carregarSelos}
                                className="px-4 py-2 text-white bg-blue-500 rounded-lg hover:bg-blue-600 focus:outline-none"
                            >
                                Mostrar Todos
                            </button>
                        </div>
                    </div>

                    <table className="w-full">
                        <thead>
                        <tr>
                            <th className="py-2 text-center px-4 border-b">Cabecote</th>
                            <th className="py-2 text-center px-4 border-b">Preco</th>
                            <th className="py-2 text-center px-4 border-b">Medida</th>
                            <th className="py-2 text-center px-4 border-b">Qtd Estoque</th>
                            <th className="py-2 text-center px-4">Excluir</th>
                            <th className="py-2 text-center px-4">Editar</th>
                        </tr>
                        </thead>
                        <tbody>
                        {selos.map((selo) => (
                            <tr key={selo.id}>
                                <td className="hidden">{selo.id}</td>
                                <td className="py-2 text-center px-4 border-b">{selo.nome}</td>
                                <td className="py-2 text-center px-4 border-b">R$ {selo.preco}</td>
                                <td className="py-2 text-center px-4 border-b">{selo.medida}</td>
                                <td className="py-2 text-center px-4 border-b">{selo.qtdEstoque}</td>
                                <button
                                    className="p-2 text-red-500 hover:text-red-700 focus:outline-none"
                                    onClick={() => deleteSelo(selo.id)}
                                >
                                    <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        className="w-5 h-5"
                                        viewBox="0 0 24 24"
                                        fill="none"
                                        stroke="currentColor"
                                        strokeWidth="2"
                                        strokeLinecap="round"
                                        strokeLinejoin="round"
                                    >
                                        <path
                                            d="M3 6l3 15h12l3-15H3zm10 3v9M8 6h8M9 3h6M4 6h16"
                                        />
                                    </svg>
                                </button>
                                <td
                                    className="bg-blue-500 cursor-pointer hover:bg-blue-600 text-white rounded py-2 px-4"
                                    onClick={() => openModal(selo.id)}
                                >
                                    <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        className="w-5 h-5 inline-block mr-1"
                                        viewBox="0 0 20 20"
                                        fill="currentColor"
                                    >
                                        <path
                                            fillRule="evenodd"
                                            d="M15.293 4.293a1 1 0 0 1 1.414 1.414l-9 9a1 1 0 0 1-1.414 0L3 12.414l-1.293 1.293A1 1 0 0 1 .293 12.293l1-1a1 1 0 0 1 1.414 0L4 12.586l7.293-7.293a1 1 0 0 1 1.414 0zM4 7v2h2L4 7zm12-3h-2V2h2v2zM7 12H5l2-2zm6-6l-1-1-2 2 1 1 2-2zm-6 0H7L5 7h2z"
                                        />
                                    </svg>
                                    Editar
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>

                    <button
                        className="bg-blue-500 text-white rounded py-2 px-4 mb-4 mt-6"
                        onClick={openModal}
                    >
                        Salvar Novo
                    </button>
                    {isModalOpen && (
                        <ModalSelo closeModal={closeModal} handleSave={handleSave} editFormData={editFormData}/>
                    )}
                </div>
            </div>
        </div>
    );
}