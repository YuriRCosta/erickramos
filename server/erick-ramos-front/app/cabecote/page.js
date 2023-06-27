'use client'

import React, {useEffect, useState} from "react";
import ModalCabecote from "../../components/ModalCabecote";
import Api from "../../services/api";
import Navbar from "../../components/Navbar";

export default function Cabecote() {
    const [cabecotes, setCabecotes] = useState([]);

    const accessToken = localStorage.getItem('accessToken');

    const handleSave = async (formData) => {
        try {
            const response = await Api.post('/cabecote', formData, {
                headers: {
                    Authorization: `Bearer ${accessToken}`
                },
            }); // Substitua pelo endpoint correto da sua API
            fetchMoreBooks();
            closeModal();
        } catch (error) {
            console.error(error);
        }
    };

    async function fetchMoreBooks() {
        const response = await Api.get('cabecote', {
            headers: {
                Authorization: `Bearer ${accessToken}`
            },
        })
        setCabecotes(response.data);
    }

    useEffect(() => {
        fetchMoreBooks();
    }, []);

    const [isModalOpen, setIsModalOpen] = useState(false);

    const openModal = () => {
        setIsModalOpen(true);
    };

    const closeModal = () => {
        setIsModalOpen(false);
    };

    const [nomePesquisa, setNomePesquisa] = useState('');

    const handleNomeChange = (e) => {
        setNomePesquisa(e.target.value);
    };

    const handlePesquisar = () => {
        if (nomePesquisa) {
            Api
                .get(`/cabecote/nome/${nomePesquisa}`, {
                    headers: {
                        Authorization: `Bearer ${accessToken}`
                    }
                })
                .then((response) => {
                    const resultado = response.data;
                    console.log(resultado);
                    setCabecotes(resultado);
                })
                .catch((error) => {
                    console.error(error);
                });
        } else {
            console.log('Informe um critério de pesquisa válido.');
        }
    };

    async function deleteCabecote(id) {
        const confirmDelete = window.confirm('Tem certeza que deseja deletar?');

        if (!confirmDelete) {
            return;
        }
        try {
            await Api.delete(`cabecote/${id}`, {
                headers: {
                    Authorization: `Bearer ${accessToken}`
                }
            });
            setCabecotes(cabecotes.filter(selo => selo.id !== id));
        } catch (e) {
            alert('Falha ao deletar cabecote, tente novamente.');
        }
    }

    return (
        <div>
            <Navbar />
            <div className="flex items-center bg-white justify-center h-screen">
                <div className="max-w-md w-full p-6 bg-neutral-500 shadow-2xl rounded-lg shadow-md text-center">
                    <h1 className="text-2xl font-semibold mb-4">Cabecotes</h1>

                    <div className="flex flex-col items-center">
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
                                onClick={fetchMoreBooks}
                                className="px-4 py-2 text-white bg-blue-500 rounded-lg hover:bg-blue-600 focus:outline-none"
                            >
                                Mostrar Todos
                            </button>
                        </div>
                    </div>
                    {/* Tabela */}
                    <table className="w-full">
                        <thead>
                        <tr>
                            <th className="py-2 text-center px-4 border-b">Cabecote</th>
                            <th className="py-2 text-center px-4 border-b">Qtd Valvulas</th>
                            <th className="py-2 text-center px-4 border-b">Material</th>
                            <th className="py-2 text-center px-4">Excluir</th>

                        </tr>
                        </thead>
                        <tbody>
                        {cabecotes.map((retentor) => (
                            <tr key={retentor.id}>
                                <td className="py-2 text-center px-4 border-b">{retentor.nome}</td>
                                <td className="py-2 text-center px-4 border-b">{retentor.qtdValvulas}V</td>
                                <td className="py-2 text-center px-4 border-b">{retentor.material}</td>
                                <button
                                    className="p-2 text-red-500 hover:text-red-700 focus:outline-none"
                                    onClick={() => deleteCabecote(retentor.id)}
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
                    {/* ModalCabecote */}
                    {isModalOpen && (
                        <ModalCabecote closeModal={closeModal} handleSave={handleSave} />
                    )}
                </div>
            </div>
        </div>
    );
}