from setuptools import setup, find_packages

with open("requirements.txt", "r") as f:
    requirements = f.read().splitlines()

setup(
    name="mini_memcached",
    version="0.1.0",
    description="A distributed in-memory caching system inspired by Facebook's Memcached.",
    long_description=open("readme.md").read(),
    long_description_content_type="text/markdown",
    author="Priyavrat",
    author_email="puniyaldev@gmail.com",
    url="https://github.com/priyavratuniyal",
    packages=find_packages(where="app"),  # Include all packages in the `app` directory
    package_dir={"": "app"},  # Root directory for packages
    include_package_data=True,
    install_requires=requirements,
    classifiers=[
        "Development Status :: 3 - Alpha",
        "Intended Audience :: Developers",
        "License :: OSI Approved :: MIT License",
        "Programming Language :: Python :: 3.8",
        "Programming Language :: Python :: 3.9",
        "Programming Language :: Python :: 3.10",
        "Topic :: Software Development :: Libraries :: Python Modules",
    ],
    python_requires=">=3.8",
    entry_points={
        "console_scripts": [
            "mini-memcached=app.main:main",  # CLI entry point
        ],
    },
)