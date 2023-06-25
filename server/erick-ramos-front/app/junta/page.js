'use client'

import React, {useEffect, useState} from "react";
import ModalJunta from "../../components/ModalJunta";
import Api from "../../services/api";
import Navbar from "../../components/Navbar";

export default function Junta() {
    const [juntas, setJuntas] = useState([]);

    const username = localStorage.getItem('username');
    const accessToken = localStorage.getItem('accessToken');

    const adicionarJunta = (novoSelo) => {
        setJuntas([...juntas, novoSelo]);
    };

    const handleSave = async (formData) => {
        try {
            const response = await Api.post('/junta', formData, {
                headers: {
                    Authorization: `Bearer ${accessToken}`
                },
            }); // Substitua pelo endpoint correto da sua API
            adicionarJunta(response.data);
            closeModal();
        } catch (error) {
            console.error(error);
        }
    };

    async function fetchMoreBooks() {
        const response = await Api.get('junta', {
            headers: {
                Authorization: `Bearer ${accessToken}`
            },
        })
        setJuntas(response.data);
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

    return (
        <div>
            <Navbar />
            <div className="flex items-center bg-white justify-center h-screen">
                <div className="max-w-md w-full p-6 bg-neutral-500 rounded-lg shadow-md text-center">
                    <h1 className="text-2xl font-semibold mb-4">Juntas</h1>

                    {/* Tabela */}
                    <table className="w-full">
                        <thead>
                        <tr>
                            <th className="py-2 text-center px-4 border-b">Retentor</th>
                            <th className="py-2 text-center px-4 border-b">Preco</th>
                            <th className="py-2 text-center px-4 border-b">Qtd Estoque</th>
                        </tr>
                        </thead>
                        <tbody>
                        {juntas.map((retentor) => (
                            <tr key={retentor.id}>
                                <td className="py-2 text-center px-4 border-b">{retentor.nome}</td>
                                <td className="py-2 text-center px-4 border-b">R$ {retentor.preco}</td>
                                <td className="py-2 text-center px-4 border-b">{retentor.qtdEstoque}</td>
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
                    {/* ModalJunta */}
                    {isModalOpen && (
                        <ModalJunta closeModal={closeModal} handleSave={handleSave} />
                    )}
                </div>
            </div>
        </div>
    );
}