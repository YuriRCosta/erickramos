'use client'

import React, {useEffect, useState} from "react";
import ModalValvula from "../../components/ModalValvula";
import Api from "../../services/api";
import Navbar from "../../components/Navbar";

export default function Valvula() {
    const [valvulas, setValvulas] = useState([]);

    const accessToken = localStorage.getItem('accessToken');

    const handleSave = async (formData) => {
        try {
            const response = await Api.post('/valvula', formData, {
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
        const response = await Api.get('valvula', {
            headers: {
                Authorization: `Bearer ${accessToken}`
            },
        })
        setValvulas(response.data);
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
                .get(`/valvula/nome/${nomePesquisa}`, {
                    headers: {
                        Authorization: `Bearer ${accessToken}`
                    }
                })
                .then((response) => {
                    const resultado = response.data;
                    console.log(resultado);
                    setValvulas(resultado);
                })
                .catch((error) => {
                    console.error(error);
                });
        } else {
            console.log('Informe um critério de pesquisa válido.');
        }
    };

    async function deleteValvula(id) {
        const confirmDelete = window.confirm('Tem certeza que deseja deletar?');

        if (!confirmDelete) {
            return;
        }
        try {
            await Api.delete(`valvula/${id}`, {
                headers: {
                    Authorization: `Bearer ${accessToken}`
                }
            });
            setValvulas(valvulas.filter(selo => selo.id !== id));
        } catch (e) {
            alert('Falha ao deletar valvula, tente novamente.');
        }
    }

    return (
        <div>
            <Navbar />
            <div className="flex items-center bg-white justify-center h-screen">
                <div className="max-w-2xl w-full p-8 bg-neutral-500 rounded-lg shadow-md text-center">
                    <h1 className="text-2xl font-semibold mb-4">Valvulas</h1>

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
                            <th className="py-2 text-center px-4 border-b">Preco</th>
                            <th className="py-2 text-center px-4 border-b">Qtd Estoque</th>
                            <th className="py-2 text-center px-4 border-b">Tipo</th>
                            <th className="py-2 text-center px-4 border-b">Codigo</th>
                            <th className="py-2 text-center px-4">Excluir</th>
                        </tr>
                        </thead>
                        <tbody>
                        {valvulas.map((retentor) => (
                            <tr key={retentor.id}>
                                <td className="py-2 text-center px-4 border-b">{retentor.nome}</td>
                                <td className="py-2 text-center px-4 border-b">R$ {retentor.preco}</td>
                                <td className="py-2 text-center px-4 border-b">{retentor.qtdEstoque}</td>
                                <td className="py-2 text-center px-4 border-b">{retentor.tipo}</td>
                                <td className="py-2 text-center px-4 border-b">{retentor.codigo}</td>
                                <button
                                    className="p-2 text-red-500 hover:text-red-700 focus:outline-none"
                                    onClick={() => deleteValvula(retentor.id)}
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
                    {/* ModalValvula */}
                    {isModalOpen && (
                        <ModalValvula closeModal={closeModal} handleSave={handleSave} />
                    )}
                </div>
            </div>
        </div>
    );
}